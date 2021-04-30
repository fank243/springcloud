package com.fank243.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Shiro Application
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:38:22
 */
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.fank243.cloud.*.**", "cn.hutool"})
@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
