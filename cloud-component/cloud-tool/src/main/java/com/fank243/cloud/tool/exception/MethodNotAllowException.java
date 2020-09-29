package com.fank243.cloud.tool.exception;


import com.fank243.cloud.tool.utils.ResultInfo;
import com.fank243.cloud.tool.enums.ResultCode;

/**
 * 拦截405
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */

public class MethodNotAllowException extends BaseException {

    public MethodNotAllowException() {
        super();
    }

    public MethodNotAllowException(String message) {
        super(message);
    }

    public MethodNotAllowException(ResultInfo result) {
        super(result);
    }

    @Override
    public String getLocalizedMessage() {
        return ResultCode.R404.getMessage();
    }
}
