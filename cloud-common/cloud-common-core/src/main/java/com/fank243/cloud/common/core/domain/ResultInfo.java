package com.fank243.cloud.common.core.domain;

import com.fank243.cloud.common.core.enums.ResultCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统统一消息组件，可用于HTTP 响应JSON串，也可用用于接收被调用层返回处理结果
 *
 * @author FanWeiJie
 * @date 2018-09-31 10:10:10
 */
@Data
public class ResultInfo<T> implements Serializable {

    /** 响应状态码 */
    private int status = ResultCodeEnum.R500.getStatus();

    /** 错误消息 */
    private String message = ResultCodeEnum.R500.getMessage();

    /** ERROR */
    private String error = "";

    /**
     * 成功标志符
     *
     * 如果成功则结果一定为true
     *
     * 如果失败则结果一定为false，同时可提供code的值作进一步处理
     */
    private boolean success = Boolean.FALSE;

    /** 时间戳 **/
    private Long timestamp = System.currentTimeMillis();

    /** 请求URI */
    private String path = "";

    private T payload;

    public boolean isSuccess() {
        return success;
    }

    public ResultInfo<T> message(String message) {
        this.message = message;
        return this;
    }

    public ResultInfo<T> payload(T payload) {
        this.payload = payload;
        return this;
    }

    public ResultInfo<T> path(String path) {
        this.path = path;
        return this;
    }

    private static <T> ResultInfo<T> builder(boolean success, int status, String message, T payload) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.success = success;
        resultInfo.status = status;
        resultInfo.message = message;
        resultInfo.payload = payload;
        return resultInfo;
    }

    private static <T> ResultInfo<T> builderSuccess(int status, String message, T payload) {
        return builder(Boolean.TRUE, status, message, payload);
    }

    private static <T> ResultInfo<T> builderSuccess(T payload) {
        return builder(Boolean.TRUE, ResultCodeEnum.R200.getStatus(), ResultCodeEnum.R200.getMessage(), payload);
    }

    private static <T> ResultInfo<T> builderFail(ResultCodeEnum resultCodeEnum) {
        return builder(Boolean.FALSE, resultCodeEnum.getStatus(), resultCodeEnum.getMessage(), null);
    }

    private static <T> ResultInfo<T> builderFail(int status, String message, T payload) {
        return builder(Boolean.FALSE, status, message, payload);
    }

    public static <T> ResultInfo<T> ok(String message, T payload) {
        return builderSuccess(ResultCodeEnum.R200.getStatus(), message, payload);
    }

    public static <T> ResultInfo<T> ok(T payload) {
        return builderSuccess(payload);
    }

    public static <T> ResultInfo<T> ok() {
        return builderSuccess(null);
    }

    public static <T> ResultInfo<T> fail(int status, String message) {
        return builderFail(status, message, null);
    }

    public static <T> ResultInfo<T> fail(int status, T payload) {
        return builderFail(status, ResultCodeEnum.R600.getMessage(), payload);
    }

    public static <T> ResultInfo<T> fail(String message) {
        return builderFail(ResultCodeEnum.R600.getStatus(), message, null);
    }

    public static <T> ResultInfo<T> fail() {
        return builderFail(ResultCodeEnum.R600.getStatus(), ResultCodeEnum.R600.getMessage(), null);
    }

    public static <T> ResultInfo<T> e401(String message) {
        return builderFail(ResultCodeEnum.R401.getStatus(), message, null);
    }

    public static <T> ResultInfo<T> e401() {
        return builderFail(ResultCodeEnum.R401);
    }

    public static <T> ResultInfo<T> e403(String message) {
        return builderFail(ResultCodeEnum.R401.getStatus(), message, null);
    }

    public static <T> ResultInfo<T> e403() {
        return builderFail(ResultCodeEnum.R403);
    }

    public static <T> ResultInfo<T> e404() {
        return builderFail(ResultCodeEnum.R404);
    }

    public static <T> ResultInfo<T> e405() {
        return builderFail(ResultCodeEnum.R405);
    }

    public static <T> ResultInfo<T> e500(String message) {
        return builderFail(ResultCodeEnum.R500.getStatus(), message, null);
    }

    public static <T> ResultInfo<T> e500() {
        return builderFail(ResultCodeEnum.R500);
    }

}