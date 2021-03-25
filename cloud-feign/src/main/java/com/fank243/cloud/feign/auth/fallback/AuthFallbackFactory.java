package com.fank243.cloud.feign.auth.fallback;

import com.fank243.cloud.feign.auth.AuthFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Hystrix 熔断处理工厂 认证鉴权模块
 *
 * @author FanWeiJie
 * @date 2021-03-26 00:02:22
 */
@Slf4j
@Component
public class AuthFallbackFactory implements FallbackFactory<AuthFeignClient> {

    @Override
    public AuthFeignClient create(Throwable throwable) {
        log.error(throwable.toString());
        return new AuthFallback();
    }
}
