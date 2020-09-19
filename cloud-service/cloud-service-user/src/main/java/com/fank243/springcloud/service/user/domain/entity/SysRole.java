package com.fank243.springcloud.service.user.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author shuang.kou
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_sys_role")
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
}
