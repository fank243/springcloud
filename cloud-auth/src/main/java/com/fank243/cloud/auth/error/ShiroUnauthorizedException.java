package com.fank243.cloud.auth.error;

import com.fank243.cloud.component.tool.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Shiro 未授权时异常拦截
 *
 * @author FanWeiJie
 * @date 2020-03-28 22:32:04
 */
@Slf4j
@RestControllerAdvice
public class ShiroUnauthorizedException {

    /** 403 **/
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultInfo handleUnauthorizedException(UnauthorizedException e) {
        if (log.isErrorEnabled()) {
            log.error(e.toString());
        }
        return ResultInfo.err403();
    }

}
