package com.fank243.cloud.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis 属性
 * 
 * @author FanWeiJie
 * @date 2021-03-21 21:22:03
 */
@Data
@Component
public class RedisProperties {

    @Value(value = "${spring.redis.host:localhost}")
    private String redisHost;

    @Value(value = "${spring.redis.port:6379}")
    private int redisPort;

    @Value(value = "${spring.redis.password:}")
    private String redisPassword;

    @Value(value = "${spring.redis.database:0}")
    private int redisDatabase;

    @Value(value = "${spring.redis.timeout:6000}")
    private int redisTimeOut;
}
