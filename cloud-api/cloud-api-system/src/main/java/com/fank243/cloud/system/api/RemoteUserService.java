package com.fank243.cloud.system.api;

import com.fank243.cloud.common.core.constant.ServiceNameConstants;
import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.system.api.factory.RemoteUserFallbackFactory;
import com.fank243.cloud.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE,
    fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    ResultInfo<LoginUser> getUserInfo(@PathVariable("username") String username);
}
