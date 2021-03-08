package com.fank243.cloud.auth.oauth2.mapper;

import com.fank243.cloud.component.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 管理员
 * 
 * @author FanWeiJie
 * @date 2020-09-25 14:42:16
 */
@Mapper
public interface SysUserMapper {

    @Select("select * from tb_sys_user where username = #{username}")
    SysUser findByUsername(String username);
}
