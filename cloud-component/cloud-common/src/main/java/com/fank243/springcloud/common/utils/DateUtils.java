package com.fank243.springcloud.common.utils;

import com.fank243.springcloud.common.constant.TimeConstants;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 常用工具类：日期转换
 * 
 * @author FanWeiJie
 * @date 2015年7月16日
 * @since JDK1.6
 */
public class DateUtils {

    /** error code **/
    public static final int ERROR = -999;

    /** yy-MM-dd **/
    public final static String YY_MM_DD = "yy-MM-dd";

    /** yyyy-MM-dd **/
    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    /** yyyy-MM-dd HH:mm:ss **/
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串转日期：自定义日期格式
     * 
     * @param date 日期字符串
     * @param pattern 自定义日期格式
     * @return Date
     * @author FanWeiJie
     * @date 2015年7月16日
     */
    public static Date strToDate(String date, String pattern) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        if (StringUtils.isBlank(pattern)) {
            format.applyPattern(YYYY_MM_DD_HH_MM_SS);
        } else {
            format.applyPattern(pattern);
        }
        format.applyPattern(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转字符串：自定义日期格式
     * 
     * @param date 日期对象
     * @param pattern 自定义日期格式
     * @return 字符串
     * @author FanWeiJie
     * @date 2015年7月16日
     */
    public static String dateToStr(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        if (StringUtils.isBlank(pattern)) {
            format.applyPattern(YYYY_MM_DD_HH_MM_SS);
        } else {
            format.applyPattern(pattern);
        }
        return format.format(date);
    }

    /**
     * 比较2个日期的大小
     * 
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 毫秒值
     * @author FanWeiJie
     * @date 2015年7月17日
     */
    public static long compareDate(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            return ERROR;
        }
        return endDate.getTime() - beginDate.getTime();
    }

    /**
     * 比较2个日期的大小(按天数 若是比较 出现错误 则 返回 -999999L)
     *
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 天数
     * @author Suhaiqiang
     * @date 2015年7月17日
     */
    public static long compareDay(Date beginDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        String beginStr = sdf.format(beginDate);
        String endStr = sdf.format(endDate);

        Date begin, end;
        try {
            begin = sdf.parse(beginStr);
            end = sdf.parse(endStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return ERROR;
        }

        return (end.getTime() - begin.getTime()) / (TimeConstants.DAY_1);
    }

    /**
     * 计算指定时间后的日期
     *
     * @param date 日期
     * @param field 日期类型，时分秒等
     * @param amount 数量
     * @return 日期
     * @author FanWeiJie
     * @date 2015年7月17日
     */
    public static Date addDate(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * add or sub n天
     *
     * @param date 日期
     * @param amount 数量
     * @return 日期
     * @author FanWeiJie
     * @date 2015年7月17日
     */
    public static Date addDay(Date date, int amount) {
        if (date == null) {
            return new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    /**
     * 计算指定日期星期几，周一为一周的开始
     * 
     * @param date 日期
     * @return 星期几
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
            return 7;
        }

        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 计算指定日期星期几，周一为一周的开始
     *
     * @param date 日期
     * @return 星期几
     */
    public static String getWeekStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
            return "星期天";
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) - 1 == 1) {
            return "星期一";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) - 1 == 2) {
            return "星期二";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) - 1 == 3) {
            return "星期三";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) - 1 == 4) {
            return "星期四";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) - 1 == 5) {
            return "星期五";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) - 1 == 6) {
            return "星期六";
        }
        return "未知";
    }

    /**
     * 计算周一
     * 
     * @param date 日期
     * @return 周一
     */
    public static Date getMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 计算周日
     *
     * @param date 日期
     * @return 周日
     */
    public static Date getSunday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day + 6);
        return cal.getTime();
    }

    /**
     * 获取
     */
    public static int get(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * 获取年
     */
    public static int getYear(Date date) {
        return get(date, Calendar.YEAR);
    }

    /**
     * 获取月
     */
    public static int getMonth(Date date) {
        return get(date, Calendar.MONTH) + 1;
    }

    /**
     * 获取当月
     */
    public static int getMonth() {
        return getMonth(new Date());
    }

    /**
     * 获取天
     */
    public static int getDay(Date date) {
        return get(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算两个日期相差天数
     * 
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 相差天数
     */
    public static int getDiffDay(Date startDate, Date endDate) {
        long days = (endDate.getTime() - startDate.getTime()) / TimeConstants.DAY_1;
        return (int)days;
    }

    /**
     * 计算两个日期相差年份数
     * 
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 相差天数
     */
    public static int getDiffYear(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(endDate);

        return calendar1.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
    }

    /**
     * 获取某月中的第一天
     *
     * @return 日期
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取某月中的最后一天
     *
     * @return 日期
     */
    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();
    }

    /**
     * 计算某个月实际天数
     *
     * @param yearMonth 年月日
     * @return 日期
     */
    public static int getMonthDays(String yearMonth) {
        int year = Integer.parseInt(yearMonth.split("-")[0]);
        int month = Integer.parseInt(yearMonth.split("-")[1]);
        Calendar calendar = Calendar.getInstance();
        // 设置年份
        calendar.set(Calendar.YEAR, year);
        // 设置当前月的上一个月
        calendar.set(Calendar.MONTH, month);
        // 获取月份中的最小值，即第一天
        int lastDay = calendar.getMinimum(Calendar.DATE);
        // 上月的第一天减去1就是当月的最后一天
        calendar.set(Calendar.DAY_OF_MONTH, lastDay - 1);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * date to chinese
     * 
     * @param date date
     * @return chinese
     */
    public static String getCnDates(Date date) {
        if (date == null) {
            return "UNKNOWN";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar now = Calendar.getInstance();

        long times = now.getTimeInMillis() - calendar.getTimeInMillis();

        // 365天，30天，24H,1H 毫秒值
        long oneMinute = TimeConstants.MIN_1, oneHour = TimeConstants.HOUR_1, oneDay = TimeConstants.HOUR_24,
            oneMonth = 30 * oneDay, oneYear = 12 * oneMonth;

        if (times < TimeConstants.MIN_1) {
            return "刚刚";
        }

        // N 分钟前
        if (times < oneHour) {
            long minutes = times / oneMinute;
            return minutes + "分钟前";
        }

        // N 个小时前
        if (times < oneDay) {
            long hours = times / oneHour;
            return hours + "小时前";
        }

        // N 天前
        if (times < oneMonth) {
            long days = times / oneDay;
            return days + "天前";
        }

        // N 个月前
        if (times < oneYear) {
            long months = times / oneMonth;
            return months + "个月前";
        }

        // N 年前
        long years = times / oneYear;
        return years + "年前";
    }

    public static void main(String[] args) {
        Date endDate = strToDate("2020-07-22 12:00:11", "yyyy-MM-dd");
        int days = (int)compareDay(endDate, new Date());
        System.out.println(days);
    }
}
