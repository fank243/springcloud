package com.fank243.cloud.system.api;

import com.fank243.cloud.common.core.constant.ServiceNameConstants;
import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.system.api.domain.SysOperLog;
import com.fank243.cloud.system.api.factory.RemoteLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 日志服务
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE,
    fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {
    /**
     * 保存系统日志
     *
     * @param sysOperLog 日志实体
     * @return 结果
     */
    @PostMapping("/operlog")
    ResultInfo<Boolean> saveLog(@RequestBody SysOperLog sysOperLog);

    /**
     * 保存访问记录
     *
     * @param username 用户名称
     * @param status 状态
     * @param message 消息
     * @return 结果
     */
    @PostMapping("/logininfor")
    ResultInfo<Boolean> saveLogininfor(@RequestParam("username") String username, @RequestParam("status") String status,
                                       @RequestParam("message") String message);
}
