package com.fank243.cloud.component.tool.exception;


import com.fank243.cloud.component.tool.utils.ResultInfo;

/**
 * 频繁提交异常
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
public class RepeatSubmitException extends BaseException {

    public RepeatSubmitException(String message) {
        super(message);
    }

    public RepeatSubmitException(ResultInfo result) {
        super(result);
    }

    @Override
    public String getLocalizedMessage() {
        return "您的提交过于频繁,请稍后再试";
    }
}
