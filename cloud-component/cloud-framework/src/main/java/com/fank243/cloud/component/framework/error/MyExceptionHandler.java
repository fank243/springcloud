package com.fank243.cloud.component.framework.error;

import com.fank243.cloud.component.common.exception.*;
import com.fank243.cloud.component.common.utils.ResultInfo;
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

    /** 400 **/
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultInfo handleBadRequestException(BadRequestException e) {
        return e.getResult() != null ? e.getResult() : ResultInfo.err400(e.getMessage());
    }

    /** 401 **/
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultInfo handleUnauthorizedException(UnauthorizedException e) {
        return e.getResult() != null ? e.getResult() : ResultInfo.err401(e.getMessage());
    }

    /** 403 **/
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultInfo handleNoHandlerFoundException(ForbiddenException e) {
        return e.getResult() != null ? e.getResult() : ResultInfo.err403(e.getMessage());
    }

    /** 404 > {@link MyErrorController} **/
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultInfo handleNoHandlerFoundException(NotFoundException e) {
        return e.getResult() != null ? e.getResult() : ResultInfo.err404(e.getMessage());
    }

    /** 405 **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultInfo handleMethodNotSupportedException(HttpServletRequest request,
        HttpRequestMethodNotSupportedException e) {
        return ResultInfo.err405().error(e.toString()).path(request.getRequestURI());
    }

    /** 自定义业务异常 **/
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultInfo handleBizException(BizException e) {
        return e.getResult() != null ? e.getResult() : ResultInfo.err500(e.getMessage());
    }

    /** 重复提交异常 **/
    @ExceptionHandler(RepeatSubmitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleRepeatSubmitException(RepeatSubmitException e) {
        return e.getResult() != null ? e.getResult() : ResultInfo.err400(e.getMessage());
    }

    /** Hibernate Validate 参数验证 **/
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultInfo handleBindException(HttpServletRequest request, BindException e) {
        return ResultInfo.err400(Objects.requireNonNull(e.getFieldError()).getDefaultMessage())
            .path(request.getRequestURI());
    }

    /** 顶层异常 **/
    @ExceptionHandler(value = BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultInfo handleBaseException(BaseException e) {
        return e.getResult() != null ? e.getResult() : ResultInfo.err500(e.getMessage());
    }

    /** 全局异常 **/
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultInfo handleException(HttpServletRequest request, Exception e) {
        return ResultInfo.err500(e.getMessage()).path(request.getRequestURI());
    }

}
