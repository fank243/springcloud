package com.fank243.cloud.common.tool.exception;

import com.fank243.cloud.common.tool.enums.ResultCode;
import com.fank243.cloud.common.tool.utils.ResultInfo;
import lombok.Getter;

/**
 * 异常基类
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
@Getter
public class BaseException extends Exception {

    private ResultInfo result;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(ResultInfo result) {
        this.result = result;
    }

    @Override
    public String getLocalizedMessage() {
        return ResultCode.R500.getMessage();
    }
}
