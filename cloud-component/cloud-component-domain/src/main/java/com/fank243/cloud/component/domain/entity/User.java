package com.fank243.cloud.component.domain.entity;

import com.fank243.cloud.component.tool.utils.EntityUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 * 
 * @author FanWeiJie
 * @date 2020-10-01 17:38:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_user")
@org.hibernate.annotations.Table(appliesTo = "tb_user", comment = "用户表")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '用户名'")
    private String username;

    @Column(name = "nickname", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '' COMMENT '昵称'")
    private String nickname;

    @Column(name = "mobile", unique = true, columnDefinition = "VARCHAR(11) NOT NULL DEFAULT '' COMMENT '手机号码'")
    private String mobile;

    @Column(name = "photo", columnDefinition = "VARCHAR(255) NOT NULL DEFAULT '' COMMENT '头像地址'")
    private String photo;

    @Column(name = "password", columnDefinition = "VARCHAR(32) NOT NULL DEFAULT '' COMMENT '登录密码'")
    private String password;

    @Column(name = "salt", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '密码加盐'")
    private String salt;

    @Column(name = "status", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '状态(0：正常，1：禁用，1：登录锁定)'")
    private Integer status;

    @Column(name = "login_err_count", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '连续登录错误次数'")
    private Integer loginErrCount;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "login_lock_time", columnDefinition = "TIMESTAMP NULL COMMENT '登录锁定时间'")
    private Date loginLockTime;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "last_login_time", columnDefinition = "TIMESTAMP NULL COMMENT '最近登录时间'")
    private Date lastLoginTime;

    @Column(name = "last_login_ip", columnDefinition = "VARCHAR(46) NULL DEFAULT '' COMMENT '最近登录IP'")
    private String lastLoginIp;

    @Column(name = "last_login_ip_addr", columnDefinition = "VARCHAR(30) NULL DEFAULT '' COMMENT '最近登录IP归属地'")
    private String lastLoginIpAddr;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已删除(0：否，1：是)'")
    private Boolean isDeleted;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "deleted_time", columnDefinition = "timestamp NULL COMMENT '删除时间'")
    private Date deletedTime;

    /** 执行 insert 语句前回调此方法 **/
    @PrePersist
    void preInsert() {
        // 字典检查及设置默认值
        EntityUtils.preCheck(this);
    }

    /** 执行 update 语句前回调此方法 **/
    @PreUpdate
    void preUpdate() {
        // 字典检查及设置默认值
        EntityUtils.preCheck(this);
    }
}
