package com.fank243.cloud.gateway.admin.config;

import com.fank243.cloud.gateway.admin.component.AuthorizationManager;
import com.fank243.cloud.gateway.admin.component.RestAuthenticationEntryPoint;
import com.fank243.cloud.gateway.admin.component.RestfulAccessDeniedHandler;
import com.fank243.cloud.gateway.core.constants.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * Spring Security Oauth2 Configuration
 *
 * @author FanWeiJie
 * @date 2020-09-26 19:08:01
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
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
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
    Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(SecurityConstants.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(SecurityConstants.AUTHORITIES_CLAIM_NAME);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
