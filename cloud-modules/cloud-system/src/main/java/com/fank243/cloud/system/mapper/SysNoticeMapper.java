package com.fank243.cloud.system.mapper;

import java.util.List;

import com.fank243.cloud.system.domain.SysNotice;

/**
 * 通知公告表 数据层
 * 
 * @author FanWeiJie
 * @date 2021-04-05 23:41:10
 */
 public interface SysNoticeMapper {
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
     SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
     List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
     int insertNotice(SysNotice notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
     int updateNotice(SysNotice notice);

    /**
     * 批量删除公告
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
     int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
     int deleteNoticeByIds(Long[] noticeIds);
}