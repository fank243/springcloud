package com.fank243.cloud.common.domain.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录表单
 * 
 * @author FanWeiJie
 * @date 2020-09-21 16:45:11
 */
@Data
public class UserFormDTO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String imgCode;
    private String smsCode;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
