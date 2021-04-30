package com.fank243.cloud.common.tool.exception;


import com.fank243.cloud.common.tool.utils.ResultInfo;
import com.fank243.cloud.common.tool.enums.ResultCode;

/**
 * 403 异常
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(ResultInfo result) {
        super(result);
    }

    @Override
    public String getLocalizedMessage() {
        return ResultCode.R401.getMessage();
    }
}