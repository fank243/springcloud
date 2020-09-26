package com.fank243.cloud.component.common.utils;

import cn.hutool.http.HttpStatus;
import com.fank243.cloud.component.common.enums.ResultCode;
import com.fank243.cloud.component.common.exception.*;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * HTTP UTILS
 * 
 * @author FanWeiJie
 * @date 2020-09-26 11:38:41
 */
public class ErrUtils {

    /**
     * 获取特定状态码友好提示信息
     * 
     * @param status HTTP STATUS
     * @return 友好提示
     */
    public static String getMessage(int status) {
        switch (status) {
            // 400
            case HttpStatus.HTTP_BAD_REQUEST:
                return ResultCode.R400.getMessage();

            // 401
            case HttpStatus.HTTP_UNAUTHORIZED:
                return ResultCode.R401.getMessage();

            // 403
            case HttpStatus.HTTP_FORBIDDEN:
                return ResultCode.R403.getMessage();

            // 404
            case HttpStatus.HTTP_NOT_FOUND:
                return ResultCode.R404.getMessage();

            // 405
            case HttpStatus.HTTP_BAD_METHOD:
                return ResultCode.R405.getMessage();

            default:
        }
        return ResultCode.R500.getMessage();
    }

    /**
     * 抛出特定HTTP 状态码异常
     * 
     * @param status HTTP STATUS
     */
    public static void exception(int status) throws Exception {
        switch (status) {
            // 400
            case HttpStatus.HTTP_BAD_REQUEST:
                throw new BadRequestException(ResultInfo.err400());

            // 401
            case HttpStatus.HTTP_UNAUTHORIZED:
                throw new UnauthorizedException(ResultInfo.err401());

            // 403
            case HttpStatus.HTTP_FORBIDDEN:
                throw new ForbiddenException(ResultInfo.err403());

            // 404
            case HttpStatus.HTTP_NOT_FOUND:
                throw new NotFoundException(ResultInfo.err404());

            // 405
            case HttpStatus.HTTP_BAD_METHOD:
                throw new HttpRequestMethodNotSupportedException(ResultCode.R405.getMessage());

            default:
        }
        throw new BaseException(ResultInfo.err500());
    }
}
