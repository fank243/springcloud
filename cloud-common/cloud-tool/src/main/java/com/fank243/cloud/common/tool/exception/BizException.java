package com.fank243.cloud.common.tool.exception;


import com.fank243.cloud.common.tool.enums.ResultCode;
import com.fank243.cloud.common.tool.utils.ResultInfo;

/**
 * 业务异常
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
public class BizException extends BaseException {

    public BizException(String message) {
        super(message);
    }

    public BizException(ResultInfo result) {
        super(result);
    }

    @Override
    public String getLocalizedMessage() {
        return ResultCode.R500.getMessage();
    }
}
