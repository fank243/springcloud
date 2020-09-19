package com.fank243.springcloud.service.user;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 * 
 * @author FanWeiJie
 * @date 2020-09-19 11:12:25
 */
@EnableMethodCache(basePackages = {"com.fank243.springcloud.service.user"})
@EnableCreateCacheAnnotation
@ComponentScan(basePackages = {"com.fank243.springcloud.service.user.*", "com.fank243.springcloud.framework.*",
    "com.fank243.springcloud.common.*"})
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
