package com.fank243.springcloud.gateway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Api 网关：后台
 * 
 * @author FanWeiJie
 * @date 2020-09-09 10:04:09
 */
@ComponentScan(basePackages = {"com.fank243.springcloud.gateway.admin.*.**", "com.fank243.springcloud.gateway.core.*"})
@EnableDiscoveryClient
@SpringBootApplication
public class AdminGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminGatewayApplication.class, args);
    }

}
