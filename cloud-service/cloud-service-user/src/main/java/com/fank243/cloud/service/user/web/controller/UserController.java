package com.fank243.cloud.service.user.web.controller;

import com.fank243.cloud.component.common.utils.WebUtils;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import com.fank243.cloud.component.domain.dto.CurrUser;
import com.fank243.cloud.component.domain.dto.UserFormDTO;
import com.fank243.cloud.component.domain.entity.SysPermission;
import com.fank243.cloud.component.domain.entity.SysRole;
import com.fank243.cloud.component.domain.entity.SysUser;
import com.fank243.cloud.service.user.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务
 * 
 * @author FanWeiJie
 * @date 2020-09-21 16:48:23
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping("/test")
    public ResultInfo validateUser(HttpServletRequest request) {
        log.info(WebUtils.getParams(request));
        return ResultInfo.ok();
    }

    @PostMapping("/validateUser")
    public ResultInfo validateUser(@RequestBody UserFormDTO userForm) {
        SysUser sysUser = sysUserService.findByUsername(userForm.getUsername());
        if (sysUser == null) {
            return ResultInfo.fail("账号或密码错误");
        }
        CurrUser currUser = new CurrUser();
        currUser.setId(sysUser.getId());
        currUser.setUsername(sysUser.getUsername());
        currUser.setPassword(sysUser.getPassword());

        List<String> permList = new ArrayList<>(1);
        for (SysRole role : sysUser.getRoles()) {
            permList = role.getPermissions().stream().map(SysPermission::getPermission).collect(Collectors.toList());
        }
        currUser.setPermList(permList);

        return ResultInfo.ok(currUser);
    }
}
