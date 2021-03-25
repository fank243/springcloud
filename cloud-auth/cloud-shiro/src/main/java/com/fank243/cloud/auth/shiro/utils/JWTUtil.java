package com.fank243.cloud.auth.shiro.utils;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * JWT Token 工具类
 * 
 * @author FanWeiJie
 * @date 2021-03-23 00:39:47
 */
@Slf4j
public class JWTUtil {

    /** Token 有效时长 2h **/
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;

    /** RefreshToken 有效时长 72h **/
    public static final long REFRESH_EXPIRE_TIME = 72 * 60 * 60 * 1000;

    /** HMAC256 密钥 **/
    private static final String TOKEN_SECRET = "ADdQDsQNv3XTUfe3loF60vvmwhH82Ns8";

    /**
     * 校验token是否正确
     * 
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    @Deprecated
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * 
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Long getCurrentTime(String token) {
        try {
            DecodedJWT decodedJwt = JWT.decode(token);
            return decodedJwt.getClaim("currentTime").asLong();
        } catch (JWTCreationException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * 
     * @param username 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    @Deprecated
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
    }

    /**
     * 签发 JWT Token
     * 
     * @param username 用户名
     * @param currentTime 当前时间
     * @return JWT Token
     */
    public static String sign(String username, Long currentTime) {

        String token = null;
        try {
            Date expireAt = new Date(currentTime + EXPIRE_TIME);
            token = JWT.create()
                // 发行人
                .withIssuer("fank243")
                // 存放数据
                .withClaim("username", username)
                // 过期时间
                .withClaim("currentTime", currentTime).withExpiresAt(expireAt).sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException e) {
            log.error("签发JWT Token异常：{}", e.toString());
        }
        return token;
    }

    /**
     * token 验证
     * 
     * @param token JWT Token
     * @return 验证结果
     */
    public static Boolean verify(String token) {
        // 创建token验证器
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("fank243").build();
        DecodedJWT decodedJwt = jwtVerifier.verify(token);

        log.debug("JWT Token验证通过：{}", JSONUtil.toJsonStr(decodedJwt));
        return true;
    }

    public static void main(String[] args) {
        String token = sign("username", System.currentTimeMillis());
        System.out.println(verify(token));
    }
}