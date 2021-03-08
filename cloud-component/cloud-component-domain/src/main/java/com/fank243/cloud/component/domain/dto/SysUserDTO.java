package com.fank243.cloud.component.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据传输对象:系统用户
 * 
 * @author FanWeiJie
 * @date 2020-09-19 19:04:47
 */
@Data
public class SysUserDTO implements Serializable {
    private Long id;

    private String username;

    private String password;

    private Integer status;
}
