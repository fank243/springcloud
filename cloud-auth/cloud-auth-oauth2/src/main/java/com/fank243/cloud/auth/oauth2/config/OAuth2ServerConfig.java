package com.fank243.cloud.auth.oauth2.config;

import com.fank243.cloud.auth.oauth2.component.JwtTokenEnhancer;
import com.fank243.cloud.auth.oauth2.service.MyUserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * 授权服务器配置
 * 
 * @author FanWeiJie
 * @date 2020-09-20 23:35:51
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    /** 注入authenticationManager 来支持 password grant type */
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenStore tokenStore;
    @Resource
    private MyUserServiceDetail myUserServiceDetail;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    /** 客户端详细信息配置 **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
        // 创建了一个client名为browser的客户端
        // .withClient("c1")
        // // 客户端安全秘钥
        // .secret(EncryptUtils.bCryptEncoder("1234"))
        // // 授权权限
        // .authorities("r1")
        // // 配置验证类型
        // .authorizedGrantTypes("password", "refresh_token")
        // // 可访问资源的授权范围
        // .scopes("all")
        // // token 有效时长
        // .accessTokenValiditySeconds(1800)
        // // refresh_token 有效时长
        // .refreshTokenValiditySeconds(50000);
    }

    /** 令牌验证以及存储配置 **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(jwtAccessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates); // 配置JWT的内容增强器
        // 配置Token的存储方式
        endpoints.tokenStore(tokenStore)
            // 适用JWT转换器
            .accessTokenConverter(jwtAccessTokenConverter())
            // 注入WebSecurityConfig配置的bean
            .authenticationManager(authenticationManager)
            // 读取用户的验证信息
            .userDetailsService(myUserServiceDetail)
            //
            .tokenEnhancer(enhancerChain);
    }

    /** 令牌安全策略配置 **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
            // 设置密码加密方式
            .passwordEncoder(passwordEncoder())
            // /oauth/token_key 申请token时不需要认证
            .tokenKeyAccess("permitAll()")
            // oauth/check_token 校验token时不需要认证
            .checkTokenAccess("permitAll()")
            // 需要设置allowFormAuthenticationForClients = true ,否则申请token返回401
            .allowFormAuthenticationForClients();
    }

    /** 认证信息存储 **/
    @Bean
    ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    /** 密码加密方式 **/
    @Bean
    PasswordEncoder passwordEncoder() {
        // 设置密码加密方式
        return new BCryptPasswordEncoder();
    }

    /** JWT TOKEN 生成策略 **/
    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        // 从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory =
            new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserServiceDetail);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
