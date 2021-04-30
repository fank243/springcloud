package com.fank243.cloud.common.domain.enums;

import lombok.Getter;

/**
 * 系统用户状态
 * 
 * @author FanWeiJie
 * @date 2021-03-21 20:27:43
 */
@Getter
public enum SysUserStatus {

    /** 正常 **/
    NORMAL(0, "正常"),

    DISABLED(1, "禁用");

    private final int code;
    private final String desc;

    SysUserStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SysUserStatus getEnum(int code) {
        SysUserStatus[] values = SysUserStatus.values();
        for (SysUserStatus status : values) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }
}
