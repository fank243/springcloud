package com.fank243.cloud.common.security.handler;

import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.common.core.exception.BaseException;
import com.fank243.cloud.common.core.exception.CustomException;
import com.fank243.cloud.common.core.exception.DemoModeException;
import com.fank243.cloud.common.core.exception.PreAuthorizeException;
import com.fank243.cloud.common.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public ResultInfo<?> baseException(BaseException e) {
        return ResultInfo.fail(e.getDefaultMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public ResultInfo<?> businessException(CustomException e) {
        if (StringUtils.isNull(e.getCode())) {
            return ResultInfo.fail(e.getMessage());
        }
        return ResultInfo.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultInfo<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultInfo.fail(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResultInfo<?> validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResultInfo.fail(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultInfo<?> validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ResultInfo.fail(e.getMessage());
    }

    /**
     * 权限异常
     */
    @ExceptionHandler(PreAuthorizeException.class)
    public ResultInfo<?> preAuthorizeException(PreAuthorizeException e) {
        return ResultInfo.e403();
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public ResultInfo<?> demoModeException(DemoModeException e) {
        return ResultInfo.e403("演示模式，不允许操作");
    }
}
