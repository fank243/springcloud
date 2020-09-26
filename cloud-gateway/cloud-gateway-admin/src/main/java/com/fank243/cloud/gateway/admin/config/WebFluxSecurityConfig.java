/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.fank243.cloud.gateway.admin.config;

import com.fank243.cloud.gateway.admin.component.AuthorizationManager;
import com.fank243.cloud.gateway.admin.component.RestAuthenticationEntryPoint;
import com.fank243.cloud.gateway.admin.component.RestfulAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    @Resource
    private AuthorizationManager authorizationManager;
    @Resource
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private RestfulAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //@formatter:off
         http.csrf().disable()
            .authorizeExchange()
            .pathMatchers("/api/oauth/**").permitAll()
             // 鉴权管理器配置
            .anyExchange().access(authorizationManager)
            .and().exceptionHandling()
             // 鉴权失败 403
            .accessDeniedHandler(accessDeniedHandler)
             // 认证失败 401
            .authenticationEntryPoint(authenticationEntryPoint)
             // oauth2
            .and().oauth2ResourceServer()
            // JWT TOKEN 验证失败或过期 401
            .authenticationEntryPoint(authenticationEntryPoint)
             // JWT 验证器
            .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
        ;
        //@formatter:on
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
