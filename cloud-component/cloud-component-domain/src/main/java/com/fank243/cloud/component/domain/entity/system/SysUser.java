package com.fank243.cloud.component.domain.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fank243.cloud.component.domain.entity.BaseEntity;
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
@TableName("tb_sys_user")
public class SysUser extends BaseEntity {

    @TableId
    private Long id;

    @TableField
    private String username;

    private String realname;

    private String mobile;

    private String email;

    private String salt;

    private String password;

    private Integer status;

    private Integer loginErrCount = 0;

    @JsonFormat(pattern = "yy/MM/dd HH:mm", timezone = "GMT+8")
    private Instant loginLockDate;

    private String lastLoginIp;

    private String lastLoginIpAddr;

    @JsonFormat(pattern = "yy/MM/dd HH:mm", timezone = "GMT+8")
    private Instant lastLoginDate;

    @TableLogic
    private Boolean isDeleted;

    @JsonFormat(pattern = "yy/MM/dd HH:mm", timezone = "GMT+8")
    private Instant deletedDate;

}
