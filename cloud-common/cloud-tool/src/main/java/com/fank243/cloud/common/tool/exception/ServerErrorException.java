package com.fank243.cloud.common.tool.exception;

import com.fank243.cloud.common.tool.enums.ResultCode;
import com.fank243.cloud.common.tool.utils.ResultInfo;

/**
 * 500 异常
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
public class ServerErrorException extends BaseException {

    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException(ResultInfo result) {
        super(result);
    }

    @Override
    public String getLocalizedMessage() {
        return ResultCode.R500.getMessage();
    }
}
