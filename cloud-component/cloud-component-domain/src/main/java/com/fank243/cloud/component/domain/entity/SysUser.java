package com.fank243.cloud.component.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * 管理员表
 * 
 * @author FanWeiJie
 * @date 2020-10-01 17:39:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity {

    private Long id;

    private String username;

    private String realname;

    private String mobile;

    private String wxNumber;

    private String email;

    private String salt;

    private String password;

    private Integer status;

    private Integer loginErrCount = 0;

    @JsonFormat(pattern = "yy/MM/dd HH:mm", timezone = "GMT+8")
    private Instant loginLockTime;

    private String lastLoginIp;

    private String lastLoginIpAddr;

    @JsonFormat(pattern = "yy/MM/dd HH:mm", timezone = "GMT+8")
    private Instant lastLoginTime;

    private Boolean isDeleted;

    @JsonFormat(pattern = "yy/MM/dd HH:mm", timezone = "GMT+8")
    private Instant deletedTime;

}
