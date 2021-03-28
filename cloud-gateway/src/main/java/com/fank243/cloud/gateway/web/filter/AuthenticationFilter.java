package com.fank243.cloud.gateway.web.filter;

import com.fank243.cloud.component.tool.enums.ResultCode;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import com.fank243.cloud.feign.auth.AuthFeignClient;
import com.fank243.cloud.gateway.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 认证授权过滤器
 *
 * 优先级 > 1
 *
 * @author FanWeiJie
 * @date 2020-09-24 15:08:41
 */
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    /** 放行白名单 **/
    private static final List<String> WHITE_URI = Arrays.asList("/api/auth/login", "/api/auth/logout");

    /** 请求头认证参数 **/
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private AuthFeignClient authFeignClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 放行白名单
        if (WHITE_URI.stream().anyMatch(s -> request.getURI().getPath().startsWith(s.replaceAll("/\\*\\*", "")))) {
            return chain.filter(exchange);
        }

        // 获取请求头认证参数
        String token = request.getHeaders().getFirst(AUTHORIZATION_HEADER);
        if (StringUtils.isBlank(token)) {
            return ResponseUtils.printJson(response, HttpStatus.UNAUTHORIZED, ResultInfo.err401());
        }

        // 校验当前登录用户是否具有访问此资源的权限
        ResultInfo result = authFeignClient.hasRight(token, request.getURI().getPath());
        if (!result.isSuccess()) {
            if (ResultCode.R400.getStatus() == result.getStatus()) {
                return ResponseUtils.printJson(response, HttpStatus.BAD_REQUEST, result);
            }
            if (ResultCode.R401.getStatus() == result.getStatus()) {
                return ResponseUtils.printJson(response, HttpStatus.UNAUTHORIZED, result);
            }
            if (ResultCode.R403.getStatus() == result.getStatus()) {
                return ResponseUtils.printJson(response, HttpStatus.FORBIDDEN, result);
            }
            if (ResultCode.R404.getStatus() == result.getStatus()) {
                return ResponseUtils.printJson(response, HttpStatus.NOT_FOUND, result);
            }
            if (ResultCode.R405.getStatus() == result.getStatus()) {
                return ResponseUtils.printJson(response, HttpStatus.METHOD_NOT_ALLOWED, result);
            }
            return ResponseUtils.printJson(response, HttpStatus.OK, result);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
