package com.fank243.cloud.auth.form;

import lombok.Data;

/**
 * 用户登录对象
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@Data
public class LoginBody {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
