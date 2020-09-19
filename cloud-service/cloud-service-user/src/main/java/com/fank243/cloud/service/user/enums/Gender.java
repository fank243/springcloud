package com.fank243.cloud.service.user.enums;

import lombok.Getter;

/**
 * 性别
 * 
 * @author FanWeiJie
 * @date 2020-03-24 17:25:44
 */
@Getter
public enum Gender {
    /** 保密 **/
    SECRET(0, "保密"),

    MALE(1, "男"),

    FEMALE(2, "女");

    Gender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public static Gender getEnum(Integer code) {
        if(code == null){
            return null;
        }
        Gender[] values = Gender.values();
        for (Gender gender : values) {
            if (gender.getCode() == code) {
                return gender;
            }
        }
        return null;
    }
}
