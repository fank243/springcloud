package com.fank243.cloud.common.core.exception.user;

import com.fank243.cloud.common.core.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author FanWeiJie \n @date 2021-04-05 23:41:10
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
