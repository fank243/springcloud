//package com.fank243.cloud.gateway.configuration;
//
//import com.fank243.cloud.gateway.component.RestAuthenticationEntryPoint;
//import com.fank243.cloud.gateway.component.RestfulAccessDeniedHandler;
//import com.fank243.cloud.gateway.component.AuthorizationManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//import javax.annotation.Resource;
//
///**
// * Spring Security Oauth2 Configuration
// *
// * @author FanWeiJie
// * @date 2020-09-26 19:08:01
// */
//@Configuration
//@EnableWebFluxSecurity
//public class WebFluxSecurityConfig {
//
//    @Resource
//    private AuthorizationManager authorizationManager;
//    @Resource
//    private RestAuthenticationEntryPoint authenticationEntryPoint;
//    @Resource
//    private RestfulAccessDeniedHandler accessDeniedHandler;
//
//    @Bean
//    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        //@formatter:off
//         http.csrf().disable()
//            .authorizeExchange()
//            .pathMatchers("/api/oauth/**").permitAll()
//             // 鉴权管理器配置
//            .anyExchange().access(authorizationManager)
//            .and().exceptionHandling()
//             // 鉴权失败 403
//            .accessDeniedHandler(accessDeniedHandler)
//             // 认证失败 401
//            .authenticationEntryPoint(authenticationEntryPoint)
//        ;
//        //@formatter:on
//        return http.build();
//    }
//
//}
