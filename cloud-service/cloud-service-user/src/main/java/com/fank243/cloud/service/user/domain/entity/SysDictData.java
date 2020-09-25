package com.fank243.cloud.service.user.domain.entity;

import com.fank243.cloud.component.domain.entity.BaseEntity;
import com.fank243.cloud.component.domain.utils.EntityUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 字典数据表
 * 
 * @author FanWeiJie
 * @date 2020-09-12 18:45:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_sys_dict_data")
@org.hibernate.annotations.Table(appliesTo = "tb_sys_dict_data", comment = "字典数据表")
public class SysDictData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dict_type", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '字典类型'")
    private String dictType;

    @Column(name = "dict_label", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '字典标签'")
    private String dictLabel;

    @Column(name = "dict_value", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '字典名称'")
    private String dictValue;

    @Column(name = "list_class", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '表格回显样式'")
    private String listClass;

    @Column(name = "is_default", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否默认（1是 0否）'")
    private Boolean isDefault;

    @Column(name = "dict_sort", columnDefinition = "INT(4) NOT NULL DEFAULT 0 COMMENT '字典排序'")
    private Integer dictSort;

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

}
