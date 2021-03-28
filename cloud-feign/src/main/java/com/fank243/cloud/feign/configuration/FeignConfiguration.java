package com.fank243.cloud.feign.configuration;

import com.fank243.cloud.feign.configuration.FeignResultDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

/**
 * Open Feign Decoder 配置
 * 
 * @author FanWeiJie
 * @date 2021-03-26 00:41:48
 */
@Configuration
public class FeignConfiguration {

    /** 解决WebFlux不能自动注入导致Gateway依赖问题启动失败 **/
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }

    @Bean
    public ErrorDecoder decoder() {
        return new FeignResultDecoder();
    }

}