package com.fank243.cloud.auth.shiro.mapper;

import com.fank243.cloud.component.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 登录接口
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:43:02
 */
@Mapper
public interface LoginMapper {

    @Select("select id,username,salt,password,`status` from tb_sys_user where is_deleted = false and username = #{username}")
    SysUser findByUsername(String username);
}
