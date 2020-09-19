package com.fank243.cloud.service.notice.web.controller;

import com.fank243.cloud.component.common.utils.ResultInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FanWeiJie
 * @date 2020-09-16 11:53:47
 */
@RequestMapping("/notice")
@RestController
public class IndexController {

    @PostMapping("")
    public ResultInfo hello() {
        return ResultInfo.ok().message("Hello World");
    }
}
