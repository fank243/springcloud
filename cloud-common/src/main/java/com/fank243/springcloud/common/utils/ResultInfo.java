package com.fank243.springcloud.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springcloud.common.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统统一消息组件，可用于HTTP 响应JSON串，也可用用于接收被调用层返回处理结果
 *
 * @author FanWeiJie
 * @date 2018-09-31 10:10:10
 */
@Data
public class ResultInfo implements Serializable {

    /** 响应状态码 */
    public int status = ResultCode.R500.getStatus();

    /** 错误消息 */
    public String message = ResultCode.R500.getMessage();

    /**
     * 成功标志符
     *
     * 如果成功则结果一定为true
     *
     * 如果失败则结果一定为false，同时可提供code的值作进一步处理
     */
    private boolean success;

    /** 时间戳 **/
    private Long timestamp = System.currentTimeMillis();

    /** 结果集中的对象 */
    private Object payload = "";

    public ResultInfo() {}

    public ResultInfo(boolean success) {
        this.timestamp = System.currentTimeMillis();
        this.success = success;
    }

    public ResultInfo(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultInfo(int status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResultInfo payload(Object payload) {
        this.payload = payload == null ? "" : payload;
        return this;
    }

    public ResultInfo code(int code) {
        this.status = code;
        return this;
    }

    public ResultInfo message(String msg) {
        this.message = msg;
        return this;
    }

    public ResultInfo success(boolean success) {
        this.success = success;
        return this;
    }

    public static ResultInfo ok() {
        return new ResultInfo().success(true).code(ResultCode.R200.getStatus()).message(ResultCode.R200.getMessage());
    }

    public static ResultInfo ok(Object payload) {
        return new ResultInfo().success(true).code(ResultCode.R200.getStatus()).message(ResultCode.R200.getMessage())
            .payload(payload);
    }

    public static ResultInfo fail() {
        return new ResultInfo().success(false).code(ResultCode.R201.getStatus()).message(ResultCode.R201.getMessage());
    }

    public static ResultInfo fail(String message) {
        return new ResultInfo().success(false).message(message).code(ResultCode.R201.getStatus());
    }

    public static ResultInfo fail(int code, String message) {
        return new ResultInfo().success(false).message(message).code(code);
    }

    public static ResultInfo notFund() {
        return new ResultInfo().success(false).message(ResultCode.R404.getMessage()).code(ResultCode.R404.getStatus());
    }

    public static ResultInfo notFund(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R404.getStatus());
    }

    public static ResultInfo methodNotSupported() {
        return new ResultInfo().success(false).message(ResultCode.R405.getMessage()).code(ResultCode.R405.getStatus());
    }

    public static ResultInfo methodNotSupported(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R405.getStatus());
    }

    public static ResultInfo unavailableService() {
        return new ResultInfo().success(false).message(ResultCode.R503.getMessage()).code(ResultCode.R503.getStatus());
    }

    public static ResultInfo unavailableService(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R503.getStatus());
    }

    public static ResultInfo unauthorized() {
        return new ResultInfo().success(false).message(ResultCode.R401.getMessage()).code(ResultCode.R401.getStatus());
    }

    public static ResultInfo unauthorized(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R401.getStatus());
    }

    public static ResultInfo expired() {
        return new ResultInfo().success(false).message(ResultCode.R402.getMessage()).code(ResultCode.R402.getStatus());
    }

    public static ResultInfo forbidden() {
        return new ResultInfo().success(false).message(ResultCode.R403.getMessage()).code(ResultCode.R403.getStatus());
    }

    public static ResultInfo forbidden(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R403.getStatus());
    }

    public static ResultInfo exception() {
        return new ResultInfo().success(false).message(ResultCode.R999.getMessage()).code(ResultCode.R999.getStatus());
    }

    public static ResultInfo exception(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R999.getStatus());
    }

    public static ResultInfo notLogin(String message) {
        return new ResultInfo().success(false).message(message).code(ResultCode.R202.getStatus());
    }

    public static ResultInfo notLogin() {
        return new ResultInfo().success(false).message(ResultCode.R202.getMessage()).code(ResultCode.R202.getStatus());
    }

    public static ResultInfo illegalParameter() {
        return new ResultInfo().success(false).message(ResultCode.R104.getMessage()).code(ResultCode.R104.getStatus());
    }

    public static ResultInfo illegalParameter(String msg) {
        return new ResultInfo().success(false).message(msg).code(ResultCode.R104.getStatus());
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }

    public JSONObject toJSON() {
        return JSON.parseObject(JSON.toJSONString(this));
    }

}