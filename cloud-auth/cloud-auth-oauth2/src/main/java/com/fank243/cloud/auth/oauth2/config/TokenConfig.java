package com.fank243.cloud.auth.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 令牌存储配置
 * 
 * @author FanWeiJie
 * @date 2020-09-20 23:28:18
 */
@Configuration
public class TokenConfig {

    @Bean
    TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
