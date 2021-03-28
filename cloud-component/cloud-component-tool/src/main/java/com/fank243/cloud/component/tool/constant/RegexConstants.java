package com.fank243.cloud.component.tool.constant;

/**
 * 正则表达式常量池
 * 
 * @author FanWeiJie
 * @date 2020-04-17 19:50:00
 */
public class RegexConstants {

    /** 正则：用户名 **/
    public static final String USERNAME = "[a-zA-Z]+[A-Za-z0-9_]{2,20}";

    /** 正则：姓名 **/
    public static final String NAME = "[\\u4E00-\\u9FA5]{2,10}";

    /** 正则：中文 > 2-20 **/
    public static final String CHINESE = "[\\u4E00-\\u9FA5]{2,20}";

    /** 正则：手机号码 **/
    public static final String MOBILE = "1[3-9]\\d{9}";

    /** 正则：常见爬虫攻击UA **/
    public static final String ILLEGAL_UA =
        "FeedDemon|JikeSpider|Indy Library|Alexa Toolbar|AskTbFXTV|AhrefsBot|CrawlDaddy|CoolpadWebkit|Java|Feedly|UniversalFeedParser|ApacheBench|Microsoft URL Control|Swiftbot|ZmEu|oBot|jaunty|Python-urllib|lightDeckReports Bot|YYSpider|DigExt|YisouSpider|HttpClient|MJ12bot|heritrix|EasouSpider|LinkpadBot|Ezooms|";

}
