package com.fank243.cloud.auth.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fank243.cloud.auth.shiro.JWTShiroUser;
import com.fank243.cloud.auth.shiro.mapper.LoginMapper;
import com.fank243.cloud.auth.shiro.service.LoginService;
import com.fank243.cloud.component.domain.entity.system.SysUser;
import com.fank243.cloud.component.domain.enums.SysUserStatus;
import com.fank243.cloud.component.tool.utils.ResultInfo;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.stereotype.Service;

/**
 * 登录服务实现类
 * 
 * @author FanWeiJie
 * @date 2021-03-24 23:13:57
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;

    public LoginServiceImpl(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Override
    public SysUser findByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return loginMapper.selectOne(wrapper);
    }

    /**
     * 登录主方法
     *
     * @param sysUser SysUser
     * @return 登录结果
     */
    @Override
    public ResultInfo login(SysUser sysUser) {
        if (SysUserStatus.DISABLED.getCode() == sysUser.getStatus()) {
            throw new LockedAccountException("账号已被锁定");
        }

        // 更新登录信息

        // 封装登录用户信息
        JWTShiroUser shiroUser = new JWTShiroUser();
        shiroUser.setId(sysUser.getId());
        shiroUser.setUsername(sysUser.getUsername());

        // 获取用户角色、权限
        return ResultInfo.ok(shiroUser);
    }
}
