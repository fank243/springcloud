package com.fank243.cloud.gateway.core.utils;

import com.fank243.cloud.component.common.utils.ResultInfo;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class ErrResult {

    public static Mono<Void> printJson(ServerHttpResponse response, HttpStatus httpStatus, ResultInfo result) {
        response.setStatusCode(httpStatus);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = response.bufferFactory().wrap(result.toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
