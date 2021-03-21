package com.fank243.cloud.auth.web.controller;

import com.fank243.cloud.auth.shiro.token.MyShiroToken;
import com.fank243.cloud.auth.shiro.token.ShiroForm;
import com.fank243.cloud.auth.shiro.utils.ShiroUtils;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录控制器
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:41:53
 */
@Slf4j
@RequestMapping("")
@RestController
public class LoginController {

    /** 登录接口 **/
    @PostMapping("/login")
    public ResultInfo login(@Valid ShiroForm shiroForm) {
        Subject subject = ShiroUtils.getSubject();

        try {
            subject.login(new MyShiroToken(shiroForm));
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            log.error(e.toString());
            return ResultInfo.fail(e.getMessage());
        } catch (AuthenticationException e) {
            log.error(e.toString());
            return ResultInfo.fail("登录失败").error(e.getMessage());
        } catch (Exception e) {
            log.error(e.toString());
            return ResultInfo.err500().error(e.getMessage());
        }
        return ResultInfo.ok().message("登录成功");
    }

    /** 未登录测试 **/
    @GetMapping("/unauthenticated/test")
    public ResultInfo unauthenticated() {
        return ResultInfo.ok();
    }

    /** 未授权测试 **/
    @RequiresRoles("admin")
    @GetMapping("/unauthorized/test/role")
    public ResultInfo unauthorized() {
        return ResultInfo.ok();
    }

    /** 未授权测试 **/
    @RequiresPermissions("auth:test")
    @GetMapping("/unauthorized/test/perm")
    public ResultInfo unauthorized2() {
        return ResultInfo.ok();
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
