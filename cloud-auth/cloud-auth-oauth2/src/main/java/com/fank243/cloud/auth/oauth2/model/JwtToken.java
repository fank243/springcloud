package com.fank243.cloud.auth.oauth2.model;

import lombok.Builder;
import lombok.Data;

/**
 * oauth2 jwt token 自定义返回参数实体
 * 
 * @author FanWeiJie
 * @date 2020-09-25 15:18:49
 */
@Data
@Builder
public class JwtToken {

    /** 访问令牌 */
    private String token;

    /** 刷新令牌 */
    private String refreshToken;

    /** 令牌类型 */
    private String tokenType;

    /** 有效时间（秒） */
    private int expiresIn;
}