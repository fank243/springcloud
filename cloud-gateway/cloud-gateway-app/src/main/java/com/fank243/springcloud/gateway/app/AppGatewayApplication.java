package com.fank243.springcloud.gateway.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Api 网关 App
 * 
 * @author FanWeiJie
 * @date 2020-09-17 10:56:45
 */
@ComponentScan(basePackages = {"com.fank243.springcloud.gateway.app.*.**", "com.fank243.springcloud.gateway.core.*"})
@EnableDiscoveryClient
@SpringBootApplication
public class AppGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppGatewayApplication.class, args);
    }
}
