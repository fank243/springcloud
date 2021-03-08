package com.fank243.cloud.service.user.mapper;

import com.fank243.cloud.component.domain.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员
 * 
 * @author FanWeiJie
 * @date 2020-09-19 20:19:33
 */
@Mapper
public interface SysUserMapper {
    /**
     * 根据用户名查找
     * 
     * @param username 用户名
     * @return SysUser
     */
    SysUser findByUsername(String username);

    /**
     * 添加记录
     * 
     * @param sysUser {@link SysUser}
     * @return 操作结果
     */
    @Insert("")
    SysUser addRecord(SysUser sysUser);
}
