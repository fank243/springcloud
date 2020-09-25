//package com.fank243.cloud.gateway.admin.component;
//
//import com.fank243.cloud.component.common.service.RedisService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.authorization.ReactiveAuthorizationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.server.authorization.AuthorizationContext;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 鉴权管理器，用于判断是否有资源的访问权限 Created by macro on 2020/6/19.
// */
//@Component
//public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
//    @Autowired
//    private RedisService redisService;
//
//    @Override
//    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
//        // 从Redis中获取当前路径可访问角色列表
//        List<String> authorities = new ArrayList<>();
//        authorities.add("admin");
//        authorities.add("user");
//        // 认证通过且角色匹配的用户可访问当前路径
//        return mono.filter(Authentication::isAuthenticated).flatMapIterable(Authentication::getAuthorities)
//            .map(GrantedAuthority::getAuthority).any(authorities::contains).map(AuthorizationDecision::new)
//            .defaultIfEmpty(new AuthorizationDecision(false));
//    }
//
//}