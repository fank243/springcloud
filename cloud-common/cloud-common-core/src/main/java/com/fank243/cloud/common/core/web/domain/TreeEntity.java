package com.fank243.cloud.common.core.web.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree基类
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TreeEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private Long parentId;

    /** 显示顺序 */
    private Integer orderNum;

    /** 祖级列表 */
    private String ancestors;

    /** 子部门 */
    private List<?> children = new ArrayList<>();
}
