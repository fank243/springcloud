package com.fank243.cloud.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * Shiro 后台登录用户
 * 
 * @author FanWeiJie
 * @date 2021-03-21 20:05:16
 */
@Data
public class JWTShiroUser implements Serializable {

    private Long id;

    private String username;
}
