package com.fank243.cloud.gateway.web.controller;

import com.fank243.cloud.component.tool.utils.ResultInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hystrix 熔断后默认回调处理控制器
 *
 * @author FanWeiJie
 * @date 2020-09-22 09:52:54
 */
@RequestMapping
@RestController
public class FallbackController {

    /** 当调用各服务失败、超时时进行熔断，此方法为全局熔断处理的默认回调方法 **/
    @RequestMapping("/fallback")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResultInfo fallback() {
        return ResultInfo.err503();
    }
}
