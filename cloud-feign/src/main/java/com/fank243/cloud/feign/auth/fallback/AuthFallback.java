package com.fank243.cloud.feign.auth.fallback;

import com.fank243.cloud.component.tool.utils.ResultInfo;
import com.fank243.cloud.feign.auth.AuthFeignClient;
import org.springframework.stereotype.Component;

/**
 * Hystrix 熔断处理器 认证鉴权模块
 *
 * @author FanWeiJie
 * @date 2021-03-26 00:02:22
 */
@Component
public class AuthFallback implements AuthFeignClient {
    @Override
    public ResultInfo hasRight(String uri) {
        return ResultInfo.fail("鉴权服务中断，请稍后再试");
    }
}
