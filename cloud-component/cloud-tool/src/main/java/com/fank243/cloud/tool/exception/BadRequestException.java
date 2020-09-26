package com.fank243.cloud.tool.exception;

import com.fank243.cloud.tool.utils.ResultInfo;
import com.fank243.cloud.tool.enums.ResultCode;

/**
 * 400 异常
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(ResultInfo result) {
        super(result);
    }

    @Override
    public String getLocalizedMessage() {
        return ResultCode.R400.getMessage();
    }
}
