/*
 * Copyright 2012-2020 the original author or authors.
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

package com.fank243.cloud.gateway.core.web.error;

import com.fank243.cloud.component.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic global {@link org.springframework.web.server.WebExceptionHandler}, rendering {@link ErrorAttributes}.
 * <p>
 * More specific errors can be handled either using Spring WebFlux abstractions (e.g. {@code @ExceptionHandler} with the
 * annotation model) or by adding {@link RouterFunction} to the chain.
 * <p>
 * This implementation will render error as HTML views if the client explicitly supports that media type. It attempts to
 * resolve error views using well known conventions. Will search for templates and static assets under {@code '/error'}
 * using the {@link HttpStatus status code} and the {@link HttpStatus#series() status series}.
 * <p>
 * For example, an {@code HTTP 404} will search (in the specific order):
 * <ul>
 * <li>{@code '/<templates>/error/404.<ext>'}</li>
 * <li>{@code '/<static>/error/404.html'}</li>
 * <li>{@code '/<templates>/error/4xx.<ext>'}</li>
 * <li>{@code '/<static>/error/4xx.html'}</li>
 * <li>{@code '/<templates>/error/error'}</li>
 * <li>{@code '/<static>/error/error.html'}</li>
 * </ul>
 * <p>
 * If none found, a default "Whitelabel Error" HTML view will be rendered.
 * <p>
 * If the client doesn't support HTML, the error information will be rendered as a JSON payload.
 *
 * @author Brian Clozel
 * @author Scott Frederick
 * @since 2.0.0
 */
@Slf4j
public class MyDefaultErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {

    /**
     * Create a new {@code DefaultErrorWebExceptionHandler} instance.
     *
     * @param errorAttributes the error attributes
     * @param resourceProperties the resources configuration properties
     * @param errorProperties the error configuration properties
     * @param applicationContext the current application context
     */
    public MyDefaultErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
        ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        // 仅返回JSON
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /** 改写成自定义异常 **/
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = super.getError(request);
        MergedAnnotation<ResponseStatus> responseStatusAnnotation = MergedAnnotations
            .from(error.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(ResponseStatus.class);
        HttpStatus httpStatus = determineHttpStatus(error, responseStatusAnnotation);

        Map<String, Object> errorAttributes = new HashMap<>(8);
        errorAttributes.put("requestId", request.exchange().getRequest().getId());
        errorAttributes.put("path", request.path());
        errorAttributes.put("status", httpStatus.value());
        errorAttributes.put("success", Boolean.FALSE);
        errorAttributes.put("message", getErrMsg(httpStatus, determineMessage(error, responseStatusAnnotation)));
        errorAttributes.put("timestamp", System.currentTimeMillis());
        errorAttributes.put("payload", "");
        return errorAttributes;
    }

    /** 根据 HTTP STATUS 返回中文友好提示 **/
    public String getErrMsg(HttpStatus httpStatus, String message) {
        if (log.isWarnEnabled()) {
            log.warn("Request Error {} {}", httpStatus.value(), message);
        }
        switch (httpStatus) {
            // 401 适用未登录或未认证
            case UNAUTHORIZED:
                return ResultCode.R401.getMessage();

            // 403 适用未获得相关角色或权限
            case FORBIDDEN:
                return ResultCode.R403.getMessage();

            // 404
            case NOT_FOUND:
                return ResultCode.R404.getMessage();

            // 405
            case METHOD_NOT_ALLOWED:
                return ResultCode.R405.getMessage();

            // 500
            case INTERNAL_SERVER_ERROR:
                return ResultCode.R500.getMessage();

            // 503
            case SERVICE_UNAVAILABLE:
                return ResultCode.R503.getMessage();

            default:
        }
        return message == null ? "未知错误" : message;
    }

    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        return super.getHttpStatus(errorAttributes);
    }

    private HttpStatus determineHttpStatus(Throwable error, MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
        if (error instanceof ResponseStatusException) {
            return ((ResponseStatusException)error).getStatus();
        }
        return responseStatusAnnotation.getValue("code", HttpStatus.class).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String determineMessage(Throwable error, MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
        if (error instanceof BindingResult) {
            return error.getMessage();
        }
        if (error instanceof ResponseStatusException) {
            return ((ResponseStatusException)error).getReason();
        }
        String reason = responseStatusAnnotation.getValue("reason", String.class).orElse("");
        if (StringUtils.hasText(reason)) {
            return reason;
        }
        return (error.getMessage() != null) ? error.getMessage() : "";
    }

}
