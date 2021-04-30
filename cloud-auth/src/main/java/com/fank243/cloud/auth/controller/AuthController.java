package com.fank243.cloud.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.fank243.cloud.auth.utils.ShiroUtils;
import com.fank243.cloud.common.tool.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 鉴权控制器
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:41:53
 */
@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthController {

    /** 鉴权接口 **/
    @PostMapping("/hasRight")
    public ResultInfo hasRight(String uri) {
        if (StrUtil.isBlank(uri)) {
            return ResultInfo.fail("接口URL必传");
        }
        Subject subject = ShiroUtils.getSubject();
        subject.isPermitted();
        return ResultInfo.ok();
    }

    /** 获取登录用户信息 **/
    @PostMapping("/getUserInfo")
    public ResultInfo getUserInfo() {
        return ResultInfo.ok().payload(ShiroUtils.getUserInfo());
    }
}
