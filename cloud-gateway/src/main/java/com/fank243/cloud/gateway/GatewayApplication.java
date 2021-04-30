package com.fank243.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Api 网关：后台
 * 
 * @author FanWeiJie
 * @date 2020-09-09 10:04:09
 */
@ConfigurationPropertiesScan(basePackages = {"com.fank243.cloud.*.**"})
@ComponentScan(basePackages = {"com.fank243.cloud.*.**"})
@EnableFeignClients("com.fank243.cloud.feign.*")
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
