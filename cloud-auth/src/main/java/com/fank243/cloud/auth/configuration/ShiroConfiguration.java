package com.fank243.cloud.auth.configuration;

import cn.hutool.core.util.StrUtil;
import com.fank243.cloud.auth.SysShiroUser;
import com.fank243.cloud.auth.filter.JWTShiroFilter;
import com.fank243.cloud.auth.filter.PermShiroFilter;
import com.fank243.cloud.auth.filter.RoleShiroFilter;
import com.fank243.cloud.auth.realm.JWTShiroRealm;
import com.fank243.cloud.auth.service.impl.ShiroServiceImpl;
import com.fank243.cloud.core.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro Configuration
 * 
 * @author FanWeiJie
 * @date 2021-03-21 17:33:39
 */
@SuppressWarnings("JavadocReference")
@Slf4j
@Configuration
public class ShiroConfiguration {

    private final RedisProperties redisProperties;

    public ShiroConfiguration(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    /**
     * 自定义Realm
     * 
     */
    @Bean
    Realm realm() {
        return new JWTShiroRealm();
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

    /**
     * 缓存管理器
     * 
     * 1.在调用{@link JWTShiroRealm#doGetAuthorizationInfo(PrincipalCollection)}方法时会将当前登录用户实体对象{@link SysShiroUser}
     * 缓存到redis中，因此SysShiroUser对象必须要实现Serializable接口，否则shiro会抛出异常
     * 
     * 2.在调用{@link com.fank243.cloud.auth.controller.LoginController#logout()}方法退出成功后会从redis中移除实体对象
     */
    @Bean
    RedisCacheManager redisCacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        // 2h
        cacheManager.setExpire(2 * 60 * 60);
        return cacheManager;
    }

    /**
     * WebSecurityManager 管理器
     */
    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义Realm
        securityManager.setRealm(realm());
        // 缓存管理器
        securityManager.setCacheManager(redisCacheManager());

        // 禁用Session，参考官方文档：http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(evaluator);

        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    @Bean
    JWTShiroFilter shiroFilter() {
        return new JWTShiroFilter();
    }

    /**
     * 定义 shiro url 拦截规则，自定义 Filter
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(ShiroServiceImpl shiroService) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());

        Map<String, Filter> filters = new LinkedHashMap<>(1);
        filters.put("jwt", shiroFilter());
        filters.put("roles", new RoleShiroFilter());
        filters.put("perms", new PermShiroFilter());
        factoryBean.setFilters(filters);

        // 动态加载
        factoryBean.setFilterChainDefinitionMap(shiroService.loadFilterChainDefinitionMap());

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

    // /** 指定密码算法规则 **/
    // @Bean
    // HashedCredentialsMatcher hashedCredentialsMatcher() {
    // HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
    // // Hash 算法
    // matcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
    // // 算法迭代次数
    // matcher.setHashIterations(ShiroUtils.HASH_ITERATIONS);
    // // 是否hex encode
    // matcher.setStoredCredentialsHexEncoded(true);
    // return matcher;
    // }

    // @Bean
    // Collection<Realm> realms() {
    // Collection<Realm> realms = new ArrayList<>(2);
    //
    // SysUserShiroRealm sysUserShiroRealm = new SysUserShiroRealm();
    // sysUserShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
    //
    // AppUserShiroRealm appUserShiroRealm = new AppUserShiroRealm();
    // appUserShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
    //
    // realms.add(sysUserShiroRealm);
    // realms.add(appUserShiroRealm);
    //
    // return realms;
    // }

}
