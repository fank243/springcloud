package com.fank243.cloud.common.core.exception;

/**
 * 验证码错误异常类
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public class CaptchaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg) {
        super(msg);
    }
}
