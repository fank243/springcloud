package com.fank243.cloud.component.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fank243.cloud.component.common.utils.encrypt.RsaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 * 
 * @author FanWeiJie
 * @date 2020-09-24 15:06:47
 */
@Slf4j
@Component
public class JwtUtils {

    private static String pubKey;

    private static String priKey;

    @Value("${fank.jwt.pri-key:}")
    public void setPriKey(String priKey) {
        JwtUtils.priKey = priKey;
    }

    @Value("${fank.jwt.pub-key:}")
    public void setPubKey(String pubKey) {
        JwtUtils.pubKey = pubKey;
    }

    private static Algorithm getAlgorithm() throws InvalidKeySpecException, NoSuchAlgorithmException {
        RSAPublicKey rsaPublicKey = (RSAPublicKey)RsaUtils.getPublicKey(pubKey);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)RsaUtils.getPrivateKey(priKey);
        return Algorithm.RSA512(rsaPublicKey, rsaPrivateKey);
    }

    /**
     * 生成RSA签名令牌
     * 
     * @param jwtId JWT ID
     * @param payload 数据
     * @param expireTime 过期时长,单位:秒
     * @return JWT令牌
     */
    public static String createToken(String jwtId, Map<String, Object> payload, int expireTime) {
        try {
            return JWT.create().withIssuer("JWT")
                // JWT ID
                .withJWTId(jwtId)
                // 签发日期
                .withIssuedAt(new Date())
                // JWT 所有人
                .withSubject("USER")
                // 过期日期
                .withExpiresAt(DateUtils.addDate(new Date(), Calendar.SECOND, expireTime))
                // 数据
                .withClaim("payload", payload)
                // 签名
                .sign(getAlgorithm());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成RSA签名令牌
     *
     * @param payload 数据
     * @param expireTime 过期时长,单位:秒
     * @return JWT令牌
     */
    public static String createToken(Map<String, Object> payload, int expireTime) {
        return createToken(StrUtils.getUUID(), payload, expireTime);
    }

    /**
     * 生成RSA签名令牌
     *
     * @param token 数据
     * @return JWT令牌
     */
    public static Map<String, Object> getClaim(String token) {
        return JWT.decode(token).getClaim("payload").asMap();
    }

    /**
     * 生成RSA签名令牌
     *
     * @param token 数据
     * @return JWT令牌
     */
    public static String getToken(String token) {
        return JWT.decode(token).getToken();
    }

    public static boolean verifyToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(getAlgorithm()).build().verify(token);
        } catch (Exception e) {
            log.error("JWT TOKEN 签名非法:{}", e.toString());
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "aaaaaaa");
        String token = createToken(map, 10000);

        System.out.println(token);
        System.out.println(getClaim(token));
        System.out.println(verifyToken(token));

        System.out.println(verifyToken(token));
    }
}
