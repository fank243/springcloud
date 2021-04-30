package com.fank243.cloud.auth;

import com.fank243.cloud.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 认证授权中心
 * 
 * @author FanWeiJie
 * @date 2021-04-04 23:05:10
 */
@EnableRyFeignClients(basePackages = {"com.fank243.cloud.*.**"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
