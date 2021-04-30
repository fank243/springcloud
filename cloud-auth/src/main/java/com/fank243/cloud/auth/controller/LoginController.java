package com.fank243.cloud.auth.controller;

import com.fank243.cloud.auth.service.LoginService;
import com.fank243.cloud.auth.token.SysShiroForm;
import com.fank243.cloud.auth.utils.JWTUtil;
import com.fank243.cloud.auth.utils.ShiroUtils;
import com.fank243.cloud.component.common.service.RedisService;
import com.fank243.cloud.component.domain.entity.system.SysUser;
import com.fank243.cloud.component.tool.constant.Constants;
import com.fank243.cloud.component.tool.constant.RedisConstants;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登录控制器
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:41:53
 */
@Slf4j
@RequestMapping("/auth")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisService redisService;

    /** 登录接口 **/
    @PostMapping("/login")
    public ResultInfo login(@Valid SysShiroForm shiroForm, HttpServletResponse response) {
        SysUser sysUser = loginService.findByUsername(shiroForm.getUsername());
        if (sysUser == null) {
            return ResultInfo.fail("用户名或密码不正确");
        }
        if (!ShiroUtils.md5Hash(shiroForm.getPassword(), sysUser.getSalt()).equalsIgnoreCase(sysUser.getPassword())) {
            return ResultInfo.fail("用户名或密码不正确");
        }

        // 签发Token
        long currentTimeMillis = System.currentTimeMillis();
        String jwtToken = JWTUtil.sign(sysUser.getUsername(), currentTimeMillis);

        redisService.set(RedisConstants.JWT_TOKEN_PRE + sysUser.getUsername(), currentTimeMillis,
            JWTUtil.REFRESH_EXPIRE_TIME);

        // header 添加 token
        response.setHeader(Constants.TOKEN_NAME, jwtToken);
        response.setHeader("Access-Control-Expose-Headers", Constants.TOKEN_NAME);

        return ResultInfo.ok().message("登录成功");
    }

    /** 登出接口 **/
    @PostMapping("/logout")
    public ResultInfo logout() {
        Subject subject = ShiroUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return ResultInfo.ok().message("账号已成功退出");
    }
}
