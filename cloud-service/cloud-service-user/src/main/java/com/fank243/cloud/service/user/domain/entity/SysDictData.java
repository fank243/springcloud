package com.fank243.cloud.service.user.domain.entity;

import com.fank243.cloud.component.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据表
 * 
 * @author FanWeiJie
 * @date 2020-09-12 18:45:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictData extends BaseEntity {

    private Long id;

    private String dictType;

    private String dictLabel;

    private String dictValue;

    private String listClass;

    private Boolean isDefault;

    private Integer dictSort;

    private Integer status;

    private String remark;
}
