package com.fank243.cloud.auth.oauth2.config;

import com.fank243.cloud.auth.oauth2.component.JwtTokenEnhancer;
import com.fank243.cloud.auth.oauth2.service.MyUserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
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
        List<TokenEnhancer> delegates = new ArrayList<>(2);
        delegates.add(jwtTokenEnhancer);
        // 适用JWT转换器
        delegates.add(accessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates);

        // @formatter:off
        endpoints.tokenStore(tokenStore)
            // 注入WebSecurityConfig配置的bean
            .authenticationManager(authenticationManager)
            // 读取用户的验证信息
            .userDetailsService(myUserServiceDetail)
            // 自定义jwt payload
            .tokenEnhancer(enhancerChain)
            .exceptionTranslator(webResponseExceptionTranslator())
        ;
        // @formatter:on
    }

    /** 令牌安全策略配置 **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        // @formatter:off
        oauthServer
            // 设置密码加密方式
            .passwordEncoder(passwordEncoder())
            // 允许通过表单方式向 /oauth/token 接口申请令牌
            .allowFormAuthenticationForClients()
            // /oauth/token_key 获取token时需要认证,免认证参数:permitAll
            .tokenKeyAccess("isAuthenticated()")
            // oauth/check_token 校验token时需要认证,免认证参数:permitAll
            .checkTokenAccess("isAuthenticated()")
            // /oauth/token_key 或者 /oauth/check_token 如果使用isAuthenticated()认证方式需要添加以下过滤器
            // 同时使用POST提交的参数有三个client_id(用户名),client_secret(用户名对应的密码),token(申请到的令牌),不需要添加Header参数Authorization
            // 需要注意的是,client_id和client_secret参数对应的是登录的用户名和密码,并非/oauth/check_token是中的参数客户端ID和客户端秘钥
            .addTokenEndpointAuthenticationFilter(checkTokenEndpointFilter())
        ;
        // @formatter:on
    }

    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                OAuth2Exception body = responseEntity.getBody();
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());

                assert body != null;
                int status = body.getHttpErrorCode();
                String message = body.getMessage();
                String oAuth2ErrorCode = body.getOAuth2ErrorCode();
                String summary = body.getSummary();

                return new ResponseEntity<>(body, headers, responseEntity.getStatusCode());
            }
        };
    }

    /** 允许以认证方式访问校验token接口 **/
    @Bean
    public ClientCredentialsTokenEndpointFilter checkTokenEndpointFilter() {
        ClientCredentialsTokenEndpointFilter filter = new ClientCredentialsTokenEndpointFilter("/oauth/check_token");
        filter.setAuthenticationManager(authenticationManager);
        filter.setAllowOnlyPost(true);
        return filter;
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
    JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    /** 加载JKS证书 **/
    @Bean
    KeyPair keyPair() {
        char[] chars = "123456".toCharArray();
        ClassPathResource resource = new ClassPathResource("jwt.jks");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, chars);
        return keyStoreKeyFactory.getKeyPair("jwt", chars);
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserServiceDetail);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
