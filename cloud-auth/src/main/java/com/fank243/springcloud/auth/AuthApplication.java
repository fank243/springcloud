package com.fank243.springcloud.auth;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * auth server
 *
 * @author FanWeiJie
 * @date 2020-09-16 10:53:08
 */
@EnableMethodCache(basePackages = {"com.fank243.springcloud"})
@EnableCreateCacheAnnotation
@ComponentScan(basePackages = {"com.fank243.springcloud.auth.*", "com.fank243.springcloud.common.*.**"})
@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
