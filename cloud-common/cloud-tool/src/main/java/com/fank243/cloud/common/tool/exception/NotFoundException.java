package com.fank243.cloud.common.tool.exception;


import com.fank243.cloud.common.tool.utils.ResultInfo;
import com.fank243.cloud.common.tool.enums.ResultCode;

/**
 * 拦截404错误页面，返回JSON错误消息
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */

public class NotFoundException extends BaseException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(ResultInfo result) {
        super(result);
    }

    @Override
    public String getLocalizedMessage() {
        return ResultCode.R404.getMessage();
    }
}
