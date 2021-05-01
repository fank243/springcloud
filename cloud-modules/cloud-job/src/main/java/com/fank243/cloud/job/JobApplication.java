package com.fank243.cloud.job;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.fank243.cloud.common.security.annotation.EnableCustomConfig;
import com.fank243.cloud.common.security.annotation.EnableRyFeignClients;
import com.fank243.cloud.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class JobApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }
}
