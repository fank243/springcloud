package com.fank243.cloud.common.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户表
 * 
 * @author FanWeiJie
 * @date 2020-10-01 17:38:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_user")
public class User extends BaseEntity {

    @TableId
    private Long id;

    private String username;

    private String nickname;

    private String mobile;

    private String photo;

    private String password;

    private String salt;

    private Integer status;

    private Integer loginErrCount;

    @JsonFormat(pattern = "yy/MM/dd")
    private Date loginLockTime;

    @JsonFormat(pattern = "yy/MM/dd")
    private Date lastLoginTime;

    private String lastLoginIp;

    private String lastLoginIpAddr;

    private Boolean isDeleted;

    @JsonFormat(pattern = "yy/MM/dd")
    private Date deletedTime;
}
