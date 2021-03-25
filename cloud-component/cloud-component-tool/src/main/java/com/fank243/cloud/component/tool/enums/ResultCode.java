package com.fank243.cloud.component.tool.enums;

import lombok.Getter;

/**
 * 统一响应代码，无特殊意义响应代码无需在此定义
 * 
 * @author FanWeiJie
 * @date 2019-05-22 15:31:44
 */
@Getter
public enum ResultCode {

    /** 操作成功 **/
    R200(200, "操作成功"),

    R201(201, "您的登录会话已失效，请重新登录"),

    R400(400, "错误的请求"),

    R401(401, "您访问的资源需要认证"),

    R402(402, "Token Expired"),

    R403(403, "您没有足够的权限访问该资源"),

    R404(404, "请求的资源不存在"),

    R405(405, "请求方式不被允许"),

    /** 系统异常 **/
    R500(500, "系统繁忙，请稍后重试"),

    R503(503, "服务暂不可用，请稍后重试"),

    R600(600, "操作失败"),

    ;

    ResultCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /** 消息代码 **/
    private final int status;
    /** 错误消息 **/
    private final String message;
}