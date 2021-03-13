package com.fank243.cloud.gateway.gateway.web.filter;

import cn.hutool.crypto.digest.DigestUtil;
import com.fank243.cloud.gateway.gateway.constants.RedisConstants;
import com.fank243.cloud.gateway.gateway.service.RedisService;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import com.fank243.cloud.gateway.gateway.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Collections;
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
    private static final List<String> WHITE_URI = Collections.singletonList("/api/oauth/**");

    @Resource
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 放行白名单
        if (WHITE_URI.stream().anyMatch(s -> request.getURI().getPath().startsWith(s.replaceAll("/\\*\\*", "")))) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst("Authorization");
        if (StringUtils.isBlank(token)) {
            return ResponseUtils.printJson(response, HttpStatus.UNAUTHORIZED, ResultInfo.err401());
        }
        // redis 校验
        Object obj = redisService.get(RedisConstants.SYS_CURR_USER);
        if (obj == null) {
            return ResponseUtils.printJson(response, HttpStatus.FORBIDDEN, ResultInfo.err403());
        }
        // 匹配
        if (!obj.toString().equalsIgnoreCase(DigestUtil.md5Hex(token))) {
            return ResponseUtils.printJson(response, HttpStatus.FORBIDDEN, ResultInfo.err403());
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
