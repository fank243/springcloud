package com.fank243.cloud.component.tool.exception;


import com.fank243.cloud.component.tool.utils.ResultInfo;
import com.fank243.cloud.component.tool.enums.ResultCode;

/**
 * 403 异常
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
public class ForbiddenException extends BaseException {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(ResultInfo result) {
        super(result);
    }

    @Override
    public String getLocalizedMessage() {
        return ResultCode.R403.getMessage();
    }
}
