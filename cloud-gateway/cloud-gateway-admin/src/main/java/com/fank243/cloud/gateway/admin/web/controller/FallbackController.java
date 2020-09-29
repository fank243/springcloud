package com.fank243.cloud.gateway.admin.web.controller;

import com.fank243.cloud.tool.utils.ResultInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断
 *
 * @author FanWeiJie
 * @date 2020-09-22 09:52:54
 */
@RequestMapping
@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public ResultInfo fallback() {
        return ResultInfo.err503();
    }
}
