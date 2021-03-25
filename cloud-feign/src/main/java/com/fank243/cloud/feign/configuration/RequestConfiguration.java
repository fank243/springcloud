package com.fank243.cloud.feign.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * 拦截Feign请求，注入请求头参数
 * 
 * @author FanWeiJie
 * @date 2021-03-26 00:41:48
 */
@Configuration
public class RequestConfiguration implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Authorization");
    }
}