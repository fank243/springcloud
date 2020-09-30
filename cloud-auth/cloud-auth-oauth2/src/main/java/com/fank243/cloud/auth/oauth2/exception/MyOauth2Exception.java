package com.fank243.cloud.auth.oauth2.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义oauth2异常JSON
 *
 * @author FanWeiJie
 * @date 2020-09-29 16:28:40
 */
@JsonSerialize(using = MyOauth2ExceptionSerializer.class)
public class MyOauth2Exception extends OAuth2Exception {

    public MyOauth2Exception(String msg) {
        super(msg);
    }
}
