package com.fank243.cloud.gateway.admin.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 读取Nacos配置属性
 *
 * @author FanWeiJie
 * @date 2019-05-19 23:27:01
 */
@Data
@RefreshScope
@Component
public class AdminGatewayProperties {

    @Value("${spring.profiles.active}")
    private String mode;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port:8001}")
    private int port;

    @Value("${spring.cloud.nacos.config.namespace}")
    private String namespace;

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;
}
