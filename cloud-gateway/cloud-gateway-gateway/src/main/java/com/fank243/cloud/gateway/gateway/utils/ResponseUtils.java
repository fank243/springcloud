package com.fank243.cloud.gateway.gateway.utils;

import com.fank243.cloud.component.tool.utils.ResultInfo;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * WebFlux 响应工具类
 * 
 * @author FanWeiJie
 * @date 2020-10-25 15:44:35
 */
public class ResponseUtils {

    /**
     * 响应 JSON
     * 
     * @param response {@link ServerHttpResponse}
     * @param httpStatus {@link HttpStatus}
     * @param result {@link ResultInfo}
     * @return void
     */
    public static Mono<Void> printJson(ServerHttpResponse response, HttpStatus httpStatus, ResultInfo result) {
        response.setStatusCode(httpStatus);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = response.bufferFactory().wrap(result.toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
