package com.fank243.springcloud.gateway.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义配置，从yml文件中读取存放在nacos中的网关路由配置data-id
 *
 * @author FanWeiJie
 * @date 2019-05-19 23:27:01
 */
@Data
@RefreshScope
@Component
@ConfigurationProperties(prefix = "fank.gateway.route")
public class FankGatewayProperties {

    /** nacos server data-id **/
    private String dataId;

    /** nacos server group **/
    private String group = "DEFAULT_GROUP";
}
