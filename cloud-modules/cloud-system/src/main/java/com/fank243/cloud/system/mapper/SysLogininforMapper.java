package com.fank243.cloud.system.mapper;

import com.fank243.cloud.system.domain.SysLogininfor;

import java.util.List;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
public interface SysLogininforMapper {
    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    int insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     * 批量删除系统登录日志
     * 
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    int deleteLogininforByIds(Long[] infoIds);

    /**
     * 清空系统登录日志
     * 
     * @return 结果
     */
    int cleanLogininfor();
}
