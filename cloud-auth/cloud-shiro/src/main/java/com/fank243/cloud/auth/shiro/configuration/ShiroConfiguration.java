package com.fank243.cloud.auth.shiro.configuration;

import cn.hutool.core.util.StrUtil;
import com.fank243.cloud.auth.shiro.filter.UnAuthenticationFilter;
import com.fank243.cloud.auth.shiro.realm.MyShiroRealm;
import com.fank243.cloud.auth.shiro.utils.ShiroUtils;
import com.fank243.cloud.component.common.constant.TimeConstants;
import com.fank243.cloud.component.common.properties.RedisProperties;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Shiro Configuration
 * 
 * @author FanWeiJie
 * @date 2021-03-21 17:33:39
 */
@Configuration
public class ShiroConfiguration {

    private final RedisProperties redisProperties;

    public ShiroConfiguration(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    /** 指定密码算法规则 **/
    @Bean
    HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // Hash 算法
        matcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        // 算法迭代次数
        matcher.setHashIterations(ShiroUtils.HASH_ITERATIONS);
        // 是否hex encode
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

    /**
     * 自定义Realm
     * 
     * @return MyShiroRealm
     */
    @Bean
    MyShiroRealm shiroRealm() {
        MyShiroRealm shiroRealm = new MyShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    @Bean
    RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getRedisHost() + ":" + redisProperties.getRedisPort());
        if (StrUtil.isNotBlank(redisProperties.getRedisPassword())) {
            redisManager.setPassword(redisProperties.getRedisPassword());
        }
        redisManager.setDatabase(redisProperties.getRedisDatabase());
        redisManager.setTimeout(redisProperties.getRedisTimeOut());
        return redisManager;
    }

    @Bean
    RedisCacheManager redisCacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        // 2h
        cacheManager.setExpire(2 * 60 * 60);
        return cacheManager;
    }

    @Bean
    SessionDAO sessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());
        return sessionDAO;
    }

    @Bean
    SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setGlobalSessionTimeout(TimeConstants.HOUR_2);

        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("shiroCookie");
        cookie.setHttpOnly(true);

        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }

    @Bean
    SimpleCookie simpleCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setMaxAge(7 * 24 * 60);
        cookie.setPath("/");
        return cookie;
    }

    @Bean
    RememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(simpleCookie());
        return rememberMeManager;
    }

    /**
     * WebSecurityManager 管理器
     */
    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 定义 shiro url 拦截规则，自定义 Filter
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());

        Map<String, String> filterMap = new HashMap(8);
        // 登录接口
        filterMap.put("/login", "anon");
        // 登出接口
        filterMap.put("/logout", "anon");
        // 默认拦截
        filterMap.put("/**", "authc");

        Map<String, Filter> filters = new HashMap<>(1);
        filters.put("authc", new UnAuthenticationFilter());

        factoryBean.setFilterChainDefinitionMap(filterMap);
        factoryBean.setFilters(filters);

        return factoryBean;
    }

    /**
     * 开启基于注解拦截，主要避免与{@code spring aop starter}的冲突导致 shiro 的注解失效
     **/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }
}
