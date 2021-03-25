package com.fank243.cloud.gateway.web.feign;

import com.fank243.cloud.feign.auth.AuthFeignClient;
import com.fank243.cloud.feign.auth.fallback.AuthFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 服务调用 鉴权模块
 * 
 * @author FanWeiJie
 * @date 2021-03-26 00:32:53
 */
@Component
@FeignClient(value = "cloud-auth", path = "/api/auth", fallbackFactory = AuthFallbackFactory.class)
public interface GatewayAuthFeignClient extends AuthFeignClient {}
