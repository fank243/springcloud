package com.fank243.cloud.common.core.exception.user;

/**
 * 验证码失效异常类
 * 
 * @author FanWeiJie \n @date 2021-04-05 23:41:10
 */
public class CaptchaExpireException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException()
    {
        super("user.jcaptcha.expire", null);
    }
}
