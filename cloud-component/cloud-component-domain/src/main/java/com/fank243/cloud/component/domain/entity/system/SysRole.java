package com.fank243.cloud.component.domain.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fank243.cloud.component.domain.entity.BaseEntity;
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
@TableName("tb_sys_role")
public class SysRole extends BaseEntity {

    @TableId
    private Long id;

    private String code;

    private String name;

    private String description;

    private Integer status;

}
