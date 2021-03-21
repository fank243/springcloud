package com.fank243.cloud.auth.shiro.service;

import cn.hutool.json.JSONObject;
import com.fank243.cloud.auth.shiro.mapper.LoginMapper;
import com.fank243.cloud.auth.shiro.ShiroUser;
import com.fank243.cloud.auth.shiro.token.ShiroForm;
import com.fank243.cloud.auth.shiro.utils.ShiroUtils;
import com.fank243.cloud.component.domain.entity.SysUser;
import com.fank243.cloud.component.domain.enums.SysUserStatus;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登录接口
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:42:44
 */
@Service
public class LoginService {

    private final LoginMapper loginMapper;

    public LoginService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    /**
     * 登录主方法
     * 
     * @param shiroForm 登录表单
     * @return 登录结果
     */
    //@Transactional(rollbackFor = Exception.class)
    public ResultInfo login(ShiroForm shiroForm) {
        SysUser sysUser = loginMapper.findByUsername(shiroForm.getUsername());
        if (sysUser == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        String md5Pwd = ShiroUtils.md5Hash(shiroForm.getPassword(), sysUser.getSalt());
        if (!md5Pwd.equalsIgnoreCase(sysUser.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        if (SysUserStatus.DISABLED.getCode() == sysUser.getStatus()) {
            throw new LockedAccountException("账号已被锁定");
        }

        // 更新登录信息

        // 封装登录用户信息
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(sysUser.getId());
        shiroUser.setUsername(sysUser.getUsername());

        // 获取用户角色、权限

        JSONObject payload = new JSONObject();
        payload.set("shiroUser", shiroUser);
        payload.set("salt", sysUser.getSalt());
        payload.set("password", sysUser.getPassword());

        return ResultInfo.ok().payload(payload);
    }

}
