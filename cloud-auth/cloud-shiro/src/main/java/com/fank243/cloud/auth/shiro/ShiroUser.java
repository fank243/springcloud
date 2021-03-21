package com.fank243.cloud.auth.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * Shiro 登录用户
 * 
 * @author FanWeiJie
 * @date 2021-03-21 20:05:16
 */
@Data
public class ShiroUser implements Serializable {
    private Long id;

    private String username;

    private Set<String> roles;

    private Set<String> perms;
}
