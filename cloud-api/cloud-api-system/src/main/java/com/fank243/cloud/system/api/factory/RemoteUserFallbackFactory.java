package com.fank243.cloud.system.api.factory;

import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.system.api.RemoteUserService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return username -> ResultInfo.fail("获取用户失败:" + throwable.getMessage());
    }
}
