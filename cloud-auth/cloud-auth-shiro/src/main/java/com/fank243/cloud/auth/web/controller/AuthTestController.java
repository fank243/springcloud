package com.fank243.cloud.auth.web.controller;

import com.fank243.cloud.component.tool.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:41:53
 */
@Slf4j
@RequestMapping("/auth/test")
@RestController
public class AuthTestController {

    /** 未登录测试 **/
    @GetMapping("/unLogin")
    public ResultInfo unLogin() {
        return ResultInfo.ok();
    }

    /** 未授权测试 **/
    @GetMapping("/perm")
    public ResultInfo perm() {
        return ResultInfo.ok();
    }

}
