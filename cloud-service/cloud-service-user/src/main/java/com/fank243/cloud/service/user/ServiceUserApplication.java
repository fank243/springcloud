package com.fank243.cloud.service.user;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户服务
 * 
 * @author FanWeiJie
 * @date 2020-09-19 11:12:25
 */
@EnableMethodCache(basePackages = {"com.fank243.cloud.service.user"})
@EnableCreateCacheAnnotation
@EntityScan(basePackages = {"com.fank243.cloud.service.*", "com.fank243.cloud.component.*.**"})
@ComponentScan(basePackages = {"com.fank243.cloud.service.*", "com.fank243.cloud.component.*.**"})
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
