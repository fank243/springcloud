package com.fank243.springcloud.gateway.core.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * sentinel
 *
 * @author FanWeiJie
 * @date 2020-09-10 17:09:42
 */
@Configuration
public class RibbonConfig {

    @Bean
    @SentinelRestTemplate
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
