package com.fank243.springcloud.gateway.web.controller;

import com.fank243.springcloud.common.utils.ResultInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hystrix 熔断
 * 
 * @author FanWeiJie
 * @date 2019-05-18 17:43:28
 */
@RestController
public class GatewayFallback {

    @RequestMapping("/fallback")
    public ResultInfo fallback() {
        return ResultInfo.unavailableService();
    }
}
