package com.fank243.cloud.component.domain.entity;

import com.fank243.cloud.component.domain.utils.EntityUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shuang.kou
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_sys_user")
@org.hibernate.annotations.Table(appliesTo = "tb_sys_user", comment = "管理员表")
public class SysUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '用户名'")
    private String username;

    @Column(name = "realname", columnDefinition = "VARCHAR(20) NULL DEFAULT '' COMMENT '姓名'")
    private String realname;

    @Column(name = "mobile", unique = true, columnDefinition = "VARCHAR(11) NOT NULL DEFAULT '' COMMENT '手机号码'")
    private String mobile;

    @Column(name = "wx_number", columnDefinition = "VARCHAR(20) NULL DEFAULT '' COMMENT '微信号码'")
    private String wxNumber;

    @Column(name = "email", columnDefinition = "VARCHAR(100) NULL DEFAULT '' COMMENT '电子邮箱'")
    private String email;

    @Column(name = "password", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '登录密码'")
    private String password;

    @Column(name = "status", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '状态(0:正常，1：禁用)'")
    private Integer status;

    @Column(name = "login_err_count", columnDefinition = "TINYINT(2) NOT NULL DEFAULT 0 COMMENT '登录错误次数'")
    private Integer loginErrCount = 0;

    @JsonFormat(pattern = "yy/MM/dd HH:mm")
    @Column(name = "login_lock_time", columnDefinition = "timestamp NULL DEFAULT NULL COMMENT '登录锁定时间'")
    private Instant loginLockTime;

    @Column(name = "last_login_ip", columnDefinition = "varchar(46) DEFAULT '' COMMENT '最后登录IP'")
    private String lastLoginIp;

    @Column(name = "last_login_ip_addr", columnDefinition = "varchar(30) DEFAULT '' COMMENT '最后登录IP归属地'")
    private String lastLoginIpAddr;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "last_login_time", columnDefinition = "timestamp NULL DEFAULT NULL COMMENT '最后登录IP'")
    private Instant lastLoginTime;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已删除(0:未删除，1：已删除)'")
    private Boolean isDeleted;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "deleted_time", columnDefinition = "timestamp NULL DEFAULT NULL COMMENT '删除时间'")
    private Instant deletedTime;

    /** 管理员与角色的关联关系 **/
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_sys_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<SysRole> roles = new HashSet<>();

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
