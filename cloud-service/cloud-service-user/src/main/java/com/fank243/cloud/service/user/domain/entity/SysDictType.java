package com.fank243.cloud.service.user.domain.entity;

import com.fank243.cloud.component.domain.entity.BaseEntity;
import com.fank243.cloud.component.tool.utils.EntityUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 字典类别表
 * 
 * @author FanWeiJie
 * @date 2020-09-12 18:45:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_sys_dict_type")
@org.hibernate.annotations.Table(appliesTo = "tb_sys_dict_type", comment = "字典类别表")
public class SysDictType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dict_type", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '字典类型'")
    private String dictType;

    @Column(name = "dict_name", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '字典名称'")
    private String dictName;

    @Column(name = "status", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '状态（0正常 1停用）'")
    private Integer status;

    @Column(name = "remark", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '备注'")
    private String remark;

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

    @Transient
    private String statusCn;

    public String getStatusCn() {
        return status == null || status == 0 ? "正常" : "禁用";
    }
}
