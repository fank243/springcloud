package com.fank243.cloud.component.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * JPA 审计实现,获取当前登录用户ID
 * 
 * @author FanWeiJie
 * @date 2020-09-19 23:21:14
 */
@Configuration
public class JpaAuditConfiguration {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        // 获取当前登录用户ID
        return () -> Optional.of(10L);
    }
}