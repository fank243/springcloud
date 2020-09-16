package com.fank243.springcloud.api.admin;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Admin Api
 * 
 * @author FanWeiJie
 * @date 2020-09-15 14:15:26
 */
@EnableMethodCache(basePackages = {"com.fank243.springcloud.api"})
@EnableCreateCacheAnnotation
@ComponentScan(basePackages = {"com.fank243.springcloud.api.*.**", "com.fank243.springcloud.common.*.**"})
@EnableDiscoveryClient
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
