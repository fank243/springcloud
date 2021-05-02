package com.fank243.cloud.gateway.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.gateway.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 自定义限流异常处理
 *
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public class SentinelFallbackHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }
        if (!BlockException.isBlockException(ex)) {
            return Mono.error(ex);
        }
        return ResponseUtils.printJson(response, HttpStatus.TOO_MANY_REQUESTS, ResultInfo.fail("请求超过最大数，请稍后再试"));
    }
}
