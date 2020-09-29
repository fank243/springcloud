//package com.fank243.cloud.gateway.admin.web.filter;
//
//import cn.hutool.crypto.digest.DigestUtil;
//import com.fank243.cloud.component.common.constant.RedisConstants;
//import com.fank243.cloud.component.common.service.RedisService;
//import com.fank243.cloud.util.utils.utils.JwtUtils;
//import com.fank243.cloud.tool.utils.ResultInfo;
//import com.fank243.cloud.util.utils.utils.StrUtils;
//import com.fank243.cloud.gateway.core.utils.ErrResult;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.util.Map;
//
///**
// * 认证授权过滤器
// *
// * 优先级 > 1
// *
// * @author FanWeiJie
// * @date 2020-09-24 15:08:41
// */
//@Component
//public class AuthenticationFilter implements GlobalFilter, Ordered {
//
//    @Resource
//    private RedisService redisService;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        String token = request.getHeaders().getFirst("Authorization");
//        if (StringUtils.isBlank(token)) {
//            return ErrResult.printJson(response, HttpStatus.UNAUTHORIZED, ResultInfo.err401());
//        }
//        if (!JwtUtils.verifyToken(token)) {
//            return ErrResult.printJson(response, HttpStatus.UNAUTHORIZED, ResultInfo.err401());
//        }
//        Map<String, Object> payload = JwtUtils.getClaim(token);
//        long userId = StrUtils.strToInt(payload.get("userId") + "", 0);
//        // redis 校验
//        Object obj = redisService.get(RedisConstants.SYS_CURR_USER + userId);
//        if (obj == null) {
//            return ErrResult.printJson(response, HttpStatus.FORBIDDEN, ResultInfo.err403());
//        }
//        // 匹配
//        if (!obj.toString().equalsIgnoreCase(DigestUtil.md5Hex(token))) {
//            return ErrResult.printJson(response, HttpStatus.FORBIDDEN, ResultInfo.err403());
//        }
//
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return 1;
//    }
//}
