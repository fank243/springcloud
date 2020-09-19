package com.fank243.cloud.component.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 常用工具：字符串操作
 * 
 * @author FanWeiJie
 * @date 2016年3月31日
 * @version ZHJR_V7.0.0
 */
public class StrUtils {

    /**
     * 将首字母转为大写
     * 
     * @param str Y 字符串
     * @return 首字母大写的字符串
     * @author FanWeiJie
     * @date 2016年3月31日
     */
    public static String firstCharToUpper(String str) {
        char[] chars = new char[1];
        chars[0] = str.charAt(0);
        String temp = new String(chars);
        if (str.charAt(0) >= 'A' && str.charAt(0) <= 'Z') {
            return str;
        }
        return str.replaceFirst(temp, temp.toUpperCase());
    }

    /***
     * 生成32位UUID值
     *
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    /**
     * 将字符串转Double类型
     *
     * @param number 字符串
     * @param defaultValue 默认值
     * @return 结果
     */
    public static Double strToDouble(String number, double defaultValue) {
        if (StringUtils.isBlank(number)) {
            return defaultValue;
        }
        try {
            return Double.valueOf(number);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转Long类型
     *
     * @param number 字符串
     * @param defaultValue 默认值
     * @return 结果
     */
    public static Long strToLong(String number, long defaultValue) {
        if (StringUtils.isBlank(number)) {
            return defaultValue;
        }
        try {
            return Long.valueOf(number);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转int类型
     *
     * @param number 字符串
     * @param defaultValue 默认值
     * @return 结果
     */
    public static int strToInt(String number, int defaultValue) {
        if (StringUtils.isBlank(number)) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static String strToStr(String str, String defaultValue) {
        if (StringUtils.isBlank(str)) {
            return defaultValue;
        }

        try {
            return str.replaceAll("null", "").replaceAll("NULL", "");
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 生成GET 请求参数串
     *
     * @param params 请求参数
     * @return 请求参数
     */
    public static String createGetParam(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                String value = URLEncoder.encode(entry.getValue(), "UTF-8");
                sb.append(entry.getKey()).append("=").append(value).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        String str = sb.toString();

        return str.substring(0, str.length() - 1);
    }

    /**
     * 生成随机字符串
     * 
     * @param length 长度
     * @return 字符串
     */
    public static String randomStr(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成随机定长数字
     *
     * @param length 长度
     * @return 字符串
     */
    public static int randomNum(int length) {
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        for (int i = 0; i < length; i++) {
            sb.append(0);
        }
        String number = new Random().nextInt(Integer.parseInt(sb.toString())) + "";
        if (number.length() != length) {
            return randomNum(length);
        }
        return Integer.parseInt(number);
    }

    /**
     * 将List集合用字符串，逗号隔开进行拼接
     * 
     * @param list
     * @return
     */
    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 转换中文数字
     * 
     * @param num 数字
     * @return 中文数字
     */
    public static String toCnNum(int num) {
        switch (num) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
            case 10:
                return "十";

            default:
                return "零";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(randomNum(8));
        }
    }

    public static String firstCharUpperCase(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
