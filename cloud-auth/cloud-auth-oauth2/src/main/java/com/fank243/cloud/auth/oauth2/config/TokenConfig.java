package com.fank243.cloud.auth.oauth2.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 令牌存储配置
 *
 * @author FanWeiJie
 * @date 2020-09-20 23:28:18
 */
@Data
@Configuration
public class TokenConfig {

    /** jwt sign key **/
    @Value("${fank.jwt.sign-key:}")
    private String signKey;

    @Resource
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        // 将生成的 token 存储在 mysql 中
        return new JdbcTokenStore(dataSource);
    }
}
