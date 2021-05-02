package com.fank243.cloud.auth.controller;

import cn.hutool.core.util.ObjectUtil;
import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.common.security.service.TokenService;
import com.fank243.cloud.system.api.model.LoginUser;
import com.fank243.cloud.auth.form.LoginBody;
import com.fank243.cloud.auth.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * token 控制
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@RequestMapping("/auth")
@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public ResultInfo<Map<String, Object>> login(@RequestBody LoginBody form) {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return ResultInfo.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public ResultInfo<?> logout(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (ObjectUtil.isNotNull(loginUser)) {
            String username = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return ResultInfo.ok();
    }

    @PostMapping("refresh")
    public ResultInfo<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (ObjectUtil.isNotNull(loginUser)) {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return ResultInfo.ok().message("刷新令牌成功");
        }
        return ResultInfo.ok();
    }
}
