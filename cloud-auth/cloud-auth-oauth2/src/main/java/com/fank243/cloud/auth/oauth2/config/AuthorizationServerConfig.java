package com.fank243.cloud.auth.oauth2.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * 授权服务器配置
 * 
 * @author FanWeiJie
 * @date 2020-09-20 23:35:51
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenStore tokenStore;
    @Resource
    private PasswordEncoder passwordEncoder;

    /** 客户端详细信息配置 **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 将客户端的信息存储在内存中
        clients.inMemory()
            // 创建了一个client名为browser的客户端
            .withClient("c1")
            // 客户端安全秘钥
            .secret("1234")
            // 授权权限
            .authorities("r1")
            // 配置验证类型
            .authorizedGrantTypes("password", "refresh_token")
            // 可访问资源的授权范围
            .scopes("all");
    }

    /** 令牌验证以及存储配置 **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 配置Token的存储方式
        endpoints.tokenStore(tokenStore)
            // 注入WebSecurityConfig配置的bean
            .authenticationManager(authenticationManager);
        // 读取用户的验证信息
        // .userDetailsService(tokenStore);
    }

    /** 令牌安全策略配置 **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        // 对获取Token的请求不再拦截
        oauthServer
            //
//            .passwordEncoder(passwordEncoder)
            //
            .tokenKeyAccess("permitAll()")
            // 验证获取Token的验证信息
            .checkTokenAccess("isAuthenticated()");
    }

}
