package com.fank243.springcloud.common.enums;

import lombok.Getter;

/**
 * 统一响应代码，无特殊意义响应代码无需在此定义
 * 
 * @author FanWeiJie
 * @date 2019-05-22 15:31:44
 */
@Getter
public enum ResultCode {

    /** 签名验证不通过 **/
    R102(102, "签名验证不通过"),

    R103(103, "非法请求"),

    R104(104, "请求参数非法"),

    R105(105, "请求受理成功"),

    /** 请求成功 **/
    R200(200, "请求成功"),

    /** 请求失败 **/
    R201(201, "请求失败"),

    /** 用户未登录 **/
    R202(202, "您的登录已失效，请重新登录"),

    R400(400, "您访问的资源已过期"),

    R401(401, "您访问的资源需要授权哦"),

    R402(402, "Token Expired"),

    R403(403, "您无权访问该资源"),

    R404(404, "请求的资源不存在"),

    R405(405, "请求方式不被支持"),

    /** 系统异常 **/
    R500(500, "系统异常"),

    R503(503, "服务暂不可用"),

    /** 系统异常 **/
    R999(999, "系统繁忙，请稍后重试");

    ResultCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /** 消息代码 **/
    private final int status;
    /** 错误消息 **/
    private final String message;
}