package com.fank243.cloud.common.core.exception.user;

/**
 * 用户密码不正确或不符合规范异常类
 * 
 * @author FanWeiJie \n @date 2021-04-05 23:41:10
 */
public class UserPasswordNotMatchException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super("user.password.not.match", null);
    }
}
