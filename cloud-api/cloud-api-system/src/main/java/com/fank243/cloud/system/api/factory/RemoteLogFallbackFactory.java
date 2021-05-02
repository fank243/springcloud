package com.fank243.cloud.system.api.factory;

import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.system.api.RemoteLogService;
import com.fank243.cloud.system.api.domain.SysOperLog;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import feign.hystrix.FallbackFactory;

/**
 * 日志服务降级处理
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@Slf4j
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService> {

    @Override
    public RemoteLogService create(Throwable throwable) {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService() {
            @Override
            public ResultInfo<Boolean> saveLog(SysOperLog sysOperLog) {
                return null;
            }

            @Override
            public ResultInfo<Boolean> saveLogininfor(String username, String status, String message) {
                return null;
            }
        };

    }
}
