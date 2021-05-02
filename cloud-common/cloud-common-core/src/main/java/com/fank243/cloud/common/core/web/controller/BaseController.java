package com.fank243.cloud.common.core.web.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

import com.fank243.cloud.common.core.constant.HttpStatus;
import com.fank243.cloud.common.core.domain.ResultInfo;
import com.fank243.cloud.common.core.web.page.PageDomain;
import com.fank243.cloud.common.core.web.page.TableDataInfo;
import com.fank243.cloud.common.core.web.page.TableSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.fank243.cloud.common.core.utils.DateUtils;
import com.fank243.cloud.common.core.utils.StringUtils;
import com.fank243.cloud.common.core.utils.sql.SqlUtil;

/**
 * web层通用数据处理
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setMsg("查询成功");
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected ResultInfo<?> toAjax(int rows) {
        return rows > 0 ? ResultInfo.ok() : ResultInfo.fail();
    }

    /**
     * 响应返回结果
     * 
     * @param result 结果
     * @return 操作结果
     */
    protected ResultInfo<?> toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public ResultInfo<?> success() {
        return ResultInfo.ok();
    }

    /**
     * 返回失败消息
     */
    public ResultInfo<?> error() {
        return ResultInfo.fail();
    }

    /**
     * 返回成功消息
     */
    public ResultInfo<?> success(String message) {
        return ResultInfo.ok(message);
    }

    /**
     * 返回失败消息
     */
    public ResultInfo<?> error(String message) {
        return ResultInfo.fail(message);
    }
}
