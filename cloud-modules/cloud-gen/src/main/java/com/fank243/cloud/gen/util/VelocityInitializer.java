package com.fank243.cloud.gen.util;

import com.fank243.cloud.common.core.constant.Constants;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * VelocityEngine工厂
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public class VelocityInitializer {
    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.ENCODING_DEFAULT, Constants.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING, Constants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
