package com.fank243.cloud.auth.token;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 后台Token实现，主要用于向Shiro提供认证数据
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:55:53
 */
@NoArgsConstructor
@AllArgsConstructor
public class SysShiroToken extends UsernamePasswordToken {

    /** 登录认证表单数据封装体 **/
    private SysShiroForm sysShiroForm;

    @Override
    public Object getPrincipal() {
        return sysShiroForm;
    }

    @Override
    public Object getCredentials() {
        return sysShiroForm.getPassword();
    }

    @Override
    public boolean isRememberMe() {
        return super.isRememberMe();
    }
}
