package com.fank243.cloud.auth.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 认证授权服务
 *
 * @author FanWeiJie
 * @date 2020-09-17 09:54:46
 */
@EnableFeignClients
@EntityScan(basePackages = {"com.fank243.cloud.auth.*.**", "com.fank243.cloud.component.*.**"})
@ComponentScan(basePackages = {"com.fank243.cloud.auth.*.**", "com.fank243.cloud.component.*.**"})
@EnableDiscoveryClient
@SpringBootApplication
public class Oauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }
}
