package com.fank243.cloud.auth.oauth2.mapper;

import com.fank243.cloud.component.domain.entity.SysRole;
import com.fank243.cloud.component.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色
 * 
 * @author FanWeiJie
 * @date 2020-09-25 14:42:16
 */
@Mapper
public interface SysRoleMapper {

    @Select("select * from tb_sys_role where id = #{sysUserid}")
    List<SysRole> findBySysUserId(Long sysUserid);
}
