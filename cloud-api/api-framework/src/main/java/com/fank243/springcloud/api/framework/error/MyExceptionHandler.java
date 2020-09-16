package com.fank243.springcloud.api.framework.error;

import com.fank243.springcloud.common.exception.BizException;
import com.fank243.springcloud.common.exception.RepeatSubmitException;
import com.fank243.springcloud.common.utils.ResultInfo;
import com.fank243.springcloud.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 异常拦截
 *
 * @author FanWeiJie
 * @date 2020-03-28 22:32:04
 */
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    /** 业务异常，控制事务回滚 **/
    @ExceptionHandler(BizException.class)
    public Object handleBizException(HttpServletRequest request, HttpServletResponse response, BizException e) {
        if (log.isErrorEnabled()) {
            log.error(e.toString(), e);
        }
        // ajax
        WebUtils.printJson(response, e.getResult());
        return null;
    }

    /** repeat submit exception **/
    @ExceptionHandler(RepeatSubmitException.class)
    public Object handleSubmitException(HttpServletResponse response, RepeatSubmitException e) {
        if (log.isInfoEnabled()) {
            log.info(e.toString());
        }
        // ajax
        WebUtils.printJson(response, e.getMessage());
        return null;
    }

    /** Ajax 参数验证 **/
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultInfo handleValidationException(BindException e) {
        if (log.isWarnEnabled()) {
            log.info(e.getBindingResult().getTarget() + "：" + e.toString());
        }
        return ResultInfo.illegalParameter(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
    }

    /** 401 **/
    // @ExceptionHandler(UnauthorizedException.class)
    // public Object handleUnauthorizedException(HttpServletRequest request, HttpServletResponse response,
    // UnauthorizedException e) {
    // if (log.isWarnEnabled()) {
    // log.error(e.toString(), e);
    // }
    // WebUtils.printJson(response, ResultInfo.unauthorized("您访问的资源需要授权哦【" + e.getMessage() + "】"));
    // return null;
    // }

    /** 404 **/
    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handleNoHandlerFoundException(HttpServletRequest request, HttpServletResponse response,
        NoHandlerFoundException e) {
        if (log.isWarnEnabled()) {
            log.warn(e.toString());
        }
        WebUtils.printJson(response, ResultInfo.notFund().message);
        return null;
    }

    /** 405 **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleMethodNotSupportedException(HttpServletRequest request, HttpServletResponse response,
        HttpRequestMethodNotSupportedException e) {
        if (log.isWarnEnabled()) {
            log.warn(e.toString());
        }
        WebUtils.printJson(response, ResultInfo.methodNotSupported());
        return null;
    }

    /** 全局异常 **/
    @ExceptionHandler(Exception.class)
    public Object handledException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (log.isErrorEnabled()) {
            log.error(e.toString(), e);
        }

        // ajax
        WebUtils.printJson(response, ResultInfo.exception("系统出错了【" + e.getMessage() + "】"));
        return null;
    }
}
