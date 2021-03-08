package com.fank243.cloud.component.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表
 * 
 * @author FanWeiJie
 * @date 2020-09-24 09:08:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity {
    private Long id;

    private String name;

    private String description;

    private Boolean enable;

}
