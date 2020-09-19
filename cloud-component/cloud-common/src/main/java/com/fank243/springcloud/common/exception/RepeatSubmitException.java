package com.fank243.springcloud.common.exception;

import lombok.Getter;

/**
 * 频繁提交异常
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
@Getter
public class RepeatSubmitException extends Exception {

    private final String message;

    public RepeatSubmitException(String message) {
        this.message = message;
    }
}
