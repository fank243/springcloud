package com.fank243.cloud.component.domain.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fank243.cloud.component.domain.entity.BaseEntity;
import com.fank243.cloud.component.domain.enums.PermissionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限权限表
 * 
 * @author FanWeiJie
 * @date 2020-03-07 22:12:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_sys_permission")
public class SysPermission extends BaseEntity {

    @TableId
    private Long id;

    private Long pid;

    private String name;

    private PermissionType type;

    private String permission;

    private String uri;

    private Boolean external;

    private String icon;

    private Integer sort;

    private Integer status;

}
