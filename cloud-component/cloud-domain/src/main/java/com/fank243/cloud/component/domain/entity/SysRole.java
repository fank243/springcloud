package com.fank243.cloud.component.domain.entity;

import com.fank243.cloud.tool.utils.EntityUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色表
 * 
 * @author FanWeiJie
 * @date 2020-09-24 09:08:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_sys_role")
@org.hibernate.annotations.Table(appliesTo = "tb_sys_role", comment = "角色表")
public class SysRole extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '角色名称'")
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(30) NULL DEFAULT '' COMMENT '角色描述'")
    private String description;

    @Column(name = "enable", columnDefinition = "TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '是否可用(0:否，1：是)'")
    private Boolean enable;

    /** 角色与权限的关联关系 **/
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_role_permission", joinColumns = {@JoinColumn(name = "role_id")},
        inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<SysPermission> permissions = new HashSet<>();

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
