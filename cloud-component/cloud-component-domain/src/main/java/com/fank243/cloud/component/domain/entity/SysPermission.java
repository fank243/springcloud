package com.fank243.cloud.component.domain.entity;

import com.fank243.cloud.component.domain.enums.PermissionType;
import com.fank243.cloud.component.tool.utils.EntityUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 权限权限表
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:12:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_sys_permission")
@org.hibernate.annotations.Table(appliesTo = "tb_sys_permission", comment = "权限表")
public class SysPermission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pid", columnDefinition = "BIGINT(20) NOT NULL DEFAULT 0 COMMENT '父节点ID(0：一级权限)'")
    private Long pid;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL DEFAULT '' COMMENT '资源名称'")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "VARCHAR(10) NOT NULL DEFAULT '' COMMENT '资源类型(button,menu)'")
    private PermissionType type;

    @Column(name = "permission", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '' COMMENT '权限'")
    private String permission;

    @Column(name = "uri", columnDefinition = "VARCHAR(255) DEFAULT '' COMMENT 'URI'")
    private String uri;

    @Column(name = "external", columnDefinition = "TINYINT(1) unsigned DEFAULT 0 COMMENT '是否外部链接(0:否,1:是)'")
    private Boolean external;

    @Column(name = "icon", columnDefinition = "VARCHAR(30) DEFAULT '' COMMENT '图标'")
    private String icon;

    @Column(name = "sort", columnDefinition = "TINYINT(2) unsigned NOT NULL DEFAULT 0 COMMENT '序号'")
    private Integer sort;

    @Column(name = "enable", columnDefinition = "TINYINT(1) unsigned NOT NULL DEFAULT 1 COMMENT '是否可用(0:否，1：是)'")
    private Boolean enable;

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
