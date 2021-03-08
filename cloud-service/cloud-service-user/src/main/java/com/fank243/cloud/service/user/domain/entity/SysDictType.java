package com.fank243.cloud.service.user.domain.entity;

import com.fank243.cloud.component.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类别表
 * 
 * @author FanWeiJie
 * @date 2020-09-12 18:45:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictType extends BaseEntity {

    private Long id;

    private String dictType;

    private String dictName;

    private Integer status;

    private String remark;

    private String statusCn;

    public String getStatusCn() {
        return status == null || status == 0 ? "正常" : "禁用";
    }
}
