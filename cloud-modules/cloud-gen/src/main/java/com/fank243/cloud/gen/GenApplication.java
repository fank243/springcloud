package com.fank243.cloud.gen;

import com.fank243.cloud.common.security.annotation.EnableCustomConfig;
import com.fank243.cloud.common.security.annotation.EnableRyFeignClients;
import com.fank243.cloud.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 代码生成
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class GenApplication {
    public static void main(String[] args) {
        SpringApplication.run(GenApplication.class, args);
    }
}
