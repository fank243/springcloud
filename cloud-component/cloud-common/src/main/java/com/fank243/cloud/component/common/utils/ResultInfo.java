package com.fank243.cloud.component.common.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fank243.cloud.component.common.enums.ResultCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
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

    /** ERROR */
    public String error = "";

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

    private Object payload = "";

    public String getMessage() {
        return StrUtil.isNotBlank(this.message) ? message : ErrUtils.getMessage(this.status);
    }

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

    public ResultInfo status(int code) {
        this.status = code;
        return this;
    }

    public ResultInfo message(String msg) {
        this.message = msg;
        return this;
    }

    public ResultInfo error(String error) {
        this.error = error;
        return this;
    }

    public ResultInfo success(boolean success) {
        this.success = success;
        return this;
    }

    public ResultInfo path(String path) {
        this.path = path;
        return this;
    }

    public static ResultInfo ok() {
        return new ResultInfo().success(true).status(ResultCode.R200.getStatus()).message(ResultCode.R200.getMessage());
    }

    public static ResultInfo ok(Object payload) {
        return new ResultInfo().success(true).status(ResultCode.R200.getStatus()).message(ResultCode.R200.getMessage())
            .payload(payload);
    }

    public static ResultInfo err400() {
        return new ResultInfo().success(false).message(ResultCode.R400.getMessage())
            .status(ResultCode.R401.getStatus());
    }

    public static ResultInfo err400(String msg) {
        return new ResultInfo().success(false).message(msg).status(ResultCode.R400.getStatus());
    }

    public static ResultInfo err401() {
        return new ResultInfo().success(false).message(ResultCode.R401.getMessage())
            .status(ResultCode.R401.getStatus());
    }

    public static ResultInfo err401(String msg) {
        return new ResultInfo().success(false).message(msg).status(ResultCode.R401.getStatus());
    }

    public static ResultInfo err402() {
        return new ResultInfo().success(false).message(ResultCode.R402.getMessage())
            .status(ResultCode.R402.getStatus());
    }

    public static ResultInfo err403() {
        return new ResultInfo().success(false).message(ResultCode.R403.getMessage())
            .status(ResultCode.R403.getStatus());
    }

    public static ResultInfo err403(String msg) {
        return new ResultInfo().success(false).message(msg).status(ResultCode.R403.getStatus());
    }

    public static ResultInfo err404() {
        return new ResultInfo().success(false).status(ResultCode.R404.getStatus())
            .message(ResultCode.R404.getMessage());
    }

    public static ResultInfo err404(String message) {
        return new ResultInfo().success(false).status(ResultCode.R404.getStatus()).message(message);
    }

    public static ResultInfo err405() {
        return new ResultInfo().success(false).message(ResultCode.R405.getMessage())
            .status(ResultCode.R405.getStatus());
    }

    public static ResultInfo err405(String message) {
        return new ResultInfo().success(false).message(message).status(ResultCode.R405.getStatus());
    }

    public static ResultInfo err500() {
        return new ResultInfo().success(false).message(ResultCode.R500.getMessage())
            .status(ResultCode.R500.getStatus());
    }

    public static ResultInfo err500(String msg) {
        return new ResultInfo().success(false).message(msg).status(ResultCode.R500.getStatus());
    }

    public static ResultInfo err500(int status, String msg) {
        return new ResultInfo().success(false).message(msg).status(status);
    }

    public static ResultInfo err503() {
        return new ResultInfo().success(false).message(ResultCode.R503.getMessage())
            .status(ResultCode.R503.getStatus());
    }

    public static ResultInfo err503(String msg) {
        return new ResultInfo().success(false).message(msg).status(ResultCode.R503.getStatus());
    }

    public static ResultInfo fail() {
        return new ResultInfo().success(false).status(ResultCode.R600.getStatus())
            .message(ResultCode.R600.getMessage());
    }

    public static ResultInfo fail(String message) {
        return new ResultInfo().success(false).message(message).status(ResultCode.R600.getStatus());
    }

    public static ResultInfo fail(int code, String message) {
        return new ResultInfo().success(false).message(message).status(code);
    }

    public String getPath() {
        if (StringUtils.isBlank(path)) {
            HttpServletRequest request = WebUtils.getRequest();
            if (request != null) {
                this.path = request.getRequestURI();
            }
        }
        return this.path;
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }

    public JSONObject toJSON() {
        return JSON.parseObject(JSON.toJSONString(this));
    }

}