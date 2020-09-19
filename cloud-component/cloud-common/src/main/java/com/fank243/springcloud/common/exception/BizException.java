package com.fank243.springcloud.common.exception;

import com.fank243.springcloud.common.utils.ResultInfo;
import lombok.Getter;

/**
 * 业务异常
 * 
 * @author FanWeiJie
 * @date 2020-03-28 23:21:47
 */
@Getter
public class BizException extends Exception {

    private ResultInfo result;

    public BizException() {
        super();
    }

    public BizException(ResultInfo result) {
        super(result.getMessage());
        this.result = result;
    }
}
