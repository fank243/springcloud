//package com.fank243.springcloud.auth.oauth2.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.fank243.springcloud.auth.oauth2.service.UserServiceDetail;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//
//import javax.annotation.Resource;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Resource
//    @Qualifier("authenticationManagerBean")
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private UserServiceDetail userServiceDetail;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        // 将客户端的信息存储在内存中
//        clients.inMemory()
//            // 创建了一个client名为browser的客户端
//            .withClient("browser")
//            // 配置验证类型
//            .authorizedGrantTypes("refresh_token", "password")
//            // 配置客户端域为“ui”
//            .scopes("ui").and().withClient("service-hi").secret("123456")
//            .authorizedGrantTypes("client_credentials", "refresh_token", "password").scopes("server");
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        // 配置Token的存储方式
//        endpoints.tokenStore(tokenStore())
//            // 注入WebSecurityConfig配置的bean
//            .authenticationManager(authenticationManager)
//            // 读取用户的验证信息
//            .userDetailsService(userServiceDetail);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
//        // 对获取Token的请求不再拦截
//        oauthServer.tokenKeyAccess("permitAll()")
//            // 验证获取Token的验证信息
//            .checkTokenAccess("isAuthenticated()");
//    }
//
//    @Resource
//    private DruidDataSource druidDataSource;
//    /**
//     * 该⽅法⽤于创建tokenStore对象（令牌存储对象）token以什么形式存储
//     */
//    public TokenStore tokenStore() {
//        // return new InMemoryTokenStore();
//        // 使⽤jwt令牌
//        return new JdbcTokenStore(druidDataSource);
//    }
//
//
//
//}
