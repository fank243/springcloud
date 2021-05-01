package com.fank243.cloud.system.api.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文件信息
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public class SysFile {
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("name", getName())
            .append("url", getUrl()).toString();
    }
}
