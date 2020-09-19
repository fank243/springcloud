package com.fank243.cloud.gateway.admin.model;

import lombok.Data;

/**
 * 登录用户实体
 * 
 * @author FanWeiJie
 * @date 2020-09-18 17:49:33
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
    private Boolean rememberMe;
}
