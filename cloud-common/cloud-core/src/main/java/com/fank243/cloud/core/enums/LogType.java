package com.fank243.cloud.core.enums;

import lombok.Getter;

/**
 * 日志类型
 * 
 * @author FanWeiJie
 * @date 2020-07-22 23:02:45
 */
@Getter
public enum LogType {

    /** 登录 **/
    LOGIN("登录"),

    ;

    private final String desc;

    LogType(String desc) {
        this.desc = desc;
    }
}
