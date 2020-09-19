package com.fank243.cloud.service.user.domain.entity;

import com.fank243.cloud.component.common.utils.EntityUtils;
import com.fank243.cloud.component.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author shuang.kou
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
