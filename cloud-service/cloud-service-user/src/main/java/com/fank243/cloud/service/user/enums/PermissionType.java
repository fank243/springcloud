package com.fank243.cloud.service.user.enums;

import lombok.Getter;

/**
 * 权限类型
 * 
 * @author FanWeiJie
 * @date 2020-03-08 15:30:01
 */
@Getter
public enum PermissionType {

    /** 按钮 **/
    BUTTON("按钮"),

    MENU("菜单");

    PermissionType(String desc) {
        this.desc = desc;
    }

    private final String desc;

    public static PermissionType getEnum(String name) {
        PermissionType[] values = PermissionType.values();
        for (PermissionType permissionType : values) {
            if (name.equalsIgnoreCase(permissionType.name())) {
                return permissionType;
            }
        }
        return null;
    }
}
