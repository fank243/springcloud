package com.fank243.cloud.component.common.exception;

import com.fank243.cloud.component.common.enums.ResultCode;
import com.fank243.cloud.component.common.utils.ResultInfo;
import lombok.Getter;

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
