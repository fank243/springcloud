package com.fank243.springcloud.framework.error;

import com.fank243.springcloud.common.exception.BizException;
import com.fank243.springcloud.common.exception.NotFoundException;
import com.fank243.springcloud.common.exception.RepeatSubmitException;
import com.fank243.springcloud.common.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 异常拦截
 *
 * @author FanWeiJie
 * @date 2020-03-28 22:32:04
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    // /** 401 **/
    // @ExceptionHandler(UnauthorizedException.class)
    // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    // public ResultInfo handleUnauthorizedException(UnauthorizedException e) {
    // return ResultInfo.R401();
    // }

    // /** 403 **/
    // @ExceptionHandler(NoHandlerFoundException.class)
    // @ResponseStatus(HttpStatus.FORBIDDEN)
    // public ResultInfo handleNoHandlerFoundException(NoHandlerFoundException e) {
    // return ResultInfo.R403();
    // }

    /** 404 > {@link MyErrorController} **/
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultInfo handleNoHandlerFoundException(HttpServletRequest request, NotFoundException e) {
        return ResultInfo.R404().path(request.getRequestURI());
    }

    /** 405 **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultInfo handleMethodNotSupportedException(HttpServletRequest request,
        HttpRequestMethodNotSupportedException e) {
        return ResultInfo.R405().path(request.getRequestURI());
    }

    /** 自定义业务异常 **/
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultInfo handleBizException(HttpServletRequest request, BizException e) {
        return e.getResult().path(request.getRequestURI());
    }

    /** 重复提交异常 **/
    @ExceptionHandler(RepeatSubmitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleRepeatSubmitException(HttpServletRequest request, RepeatSubmitException e) {
        return ResultInfo.fail(e.getMessage()).path(request.getRequestURI());
    }

    /** Hibernate Validate 参数验证 **/
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultInfo handleBindException(HttpServletRequest request, BindException e) {
        return ResultInfo.fail(Objects.requireNonNull(e.getFieldError()).getDefaultMessage())
            .path(request.getRequestURI());
    }

    /** 全局异常 **/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultInfo handleException(HttpServletRequest request, Exception e) {
        return ResultInfo.error().path(request.getRequestURI());
    }

}
