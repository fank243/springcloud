package com.fank243.cloud.component.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.ParseException;

/**
 * JPA 实体工具类
 * 
 * @author FanWeiJie
 * @date 2020-09-19 22:44:43
 */
public class EntityUtils {

    /**
     * 通过反射对插入数据库的字段进行检查并替换为默认值
     * 
     * @param obj 目标 Entity
     */
    public static void preCheck(Object obj) {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                // 字段类型名称
                String fieldTypeName = field.getType().getName();
                // 字段值
                Object fieldValue = field.get(obj);

                if (Long.class.getName().equals(fieldTypeName) && fieldValue == null) {
                    field.set(obj, 0L);
                } else if (Integer.class.getName().equals(fieldTypeName) && fieldValue == null) {
                    field.set(obj, 0);
                } else if (Double.class.getName().equals(fieldTypeName) && fieldValue == null) {
                    field.set(obj, 0.00D);
                } else if (Boolean.class.getName().equals(fieldTypeName) && fieldValue == null) {
                    field.set(obj, Boolean.FALSE);
                } else if (String.class.getName().equals(fieldTypeName)) {
                    // 替换前后空格,null后设置默认值空串
                    field.set(obj, StringUtils.trimToEmpty(StrUtils.strToStr(String.valueOf(fieldValue), "")));
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
