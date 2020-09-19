package com.fank243.springcloud.auth.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 认证授权服务
 * 
 * @author FanWeiJie
 * @date 2020-09-17 09:54:46
 */
@ComponentScan(basePackages = {"com.fank243.springcloud.auth.oauth2.*", "com.fank243.springcloud.framework.*",
    "com.fank243.springcloud.common.*"})
@EnableResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class Oauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }
}
