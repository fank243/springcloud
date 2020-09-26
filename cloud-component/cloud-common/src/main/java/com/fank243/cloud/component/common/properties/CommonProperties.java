package com.fank243.cloud.component.common.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "fank")
public class CommonProperties {

    @Value("${fank.encrypt.des-key:}")
    private String desKey;
}
