package com.fank243.cloud.auth.oauth2.mapper;

import com.fank243.cloud.component.domain.entity.SysPermission;
import com.fank243.cloud.component.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限
 * 
 * @author FanWeiJie
 * @date 2020-09-25 14:42:16
 */
@Mapper
public interface SysPermMapper {

    @Select("select * from tb_sys_role_permission a left join tb_sys_permission b on a.role_id = b.id where a.role_id = #{roleId}")
    List<SysPermission> findByRoleId(Long roleId);
}
