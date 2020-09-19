package com.fank243.springcloud.service.user.domain.entity;

import com.fank243.springcloud.service.user.enums.PermissionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
public class SysPermission extends BaseEntity implements Serializable {

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

    @Column(name = "available", columnDefinition = "TINYINT(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否可用(0:否，1：是)'")
    private Boolean available;

    @JsonFormat(pattern = "yy/MM/dd")
    @Column(name = "gmt_created", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date gmtCreated;

    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @Column(name = "gmt_modified",
        columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近修改日期'")
    private Date gmtModified;

    @Transient
    private String pidName;

}
