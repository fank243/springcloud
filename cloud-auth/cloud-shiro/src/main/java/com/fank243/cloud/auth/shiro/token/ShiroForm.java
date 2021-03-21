package com.fank243.cloud.auth.shiro.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Shiro 认证表单数据封装实体
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:56:25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiroForm {

    @NotBlank(message = "请填写用户名")
    private String username;

    @NotBlank(message = "请填写登录密码")
    private String password;

    private boolean isRememberMe;
}
