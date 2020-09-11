package com.fank243.springcloud.gateway.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fank243.springcloud.common.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hystrix 熔断
 * 
 * @author FanWeiJie
 * @date 2019-05-18 17:43:28
 */
@RestController
public class GatewayController {

    @SentinelResource(value = "index", fallback = "fallback")
    @RequestMapping("/index")
    public ResultInfo index() {
        return ResultInfo.unavailableService();
    }

    public ResultInfo fallback() {
        return ResultInfo.exception();
    }
}
