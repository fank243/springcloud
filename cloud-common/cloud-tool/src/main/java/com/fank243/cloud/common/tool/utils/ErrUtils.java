package com.fank243.cloud.common.tool.utils;

import cn.hutool.http.HttpStatus;
import com.fank243.cloud.common.tool.enums.ResultCode;
import com.fank243.cloud.common.tool.exception.*;

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
     * 根据状态码获取异常数据
     *
     * @param status HTTP STATUS
     * @param error exception stack
     * @return 友好提示
     */
    public static BaseException getResult(int status, String error) {
        ResultInfo result = new ResultInfo();
        result.setError(error);
        switch (status) {
            // 400
            case HttpStatus.HTTP_BAD_REQUEST:
                result = ResultInfo.err400();
                return new BadRequestException(result);

            // 401
            case HttpStatus.HTTP_UNAUTHORIZED:
                result = ResultInfo.err401();
                return new UnauthorizedException(result);

            // 403
            case HttpStatus.HTTP_FORBIDDEN:
                result = ResultInfo.err403();
                return new ForbiddenException(result);

            // 404
            case HttpStatus.HTTP_NOT_FOUND:
                result = ResultInfo.err404();
                return new NotFoundException(result);

            // 405
            case HttpStatus.HTTP_BAD_METHOD:
                result = ResultInfo.err405();
                return new MethodNotAllowException(result);

            default:
                result = ResultInfo.err500();
                return new ServerErrorException(result);
        }
    }

    /**
     * 抛出特定HTTP 状态码异常
     * 
     * @param status HTTP STATUS
     */
    public static BaseException exception(int status, ResultInfo result) {
        switch (status) {
            // 400
            case HttpStatus.HTTP_BAD_REQUEST:
                return new BadRequestException(result);

            // 401
            case HttpStatus.HTTP_UNAUTHORIZED:
                return new UnauthorizedException(result);

            // 403
            case HttpStatus.HTTP_FORBIDDEN:
                return new ForbiddenException(result);

            // 404
            case HttpStatus.HTTP_NOT_FOUND:
                return new NotFoundException(result);

            // 405
            case HttpStatus.HTTP_BAD_METHOD:
                return new MethodNotAllowException(result);

            default:
        }
        return new BaseException(result);
    }
}
