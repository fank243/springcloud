package com.fank243.cloud.auth.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.cloud.component.domain.entity.system.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统权限
 * 
 * @author FanWeiJie
 * @date 2021-03-24 20:40:32
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    @Select("select a.id,a.permission from tb_sys_permission a left join tb_sys_role_permission b on a.id = b.permission_id where a.`status` = 0 and b.role_id = #{roleId} and a.type = #{permType}")
    List<SysPermission> findByRoleIdAndPermType(@Param("roleId") Long roleId, @Param("permType") String permType);
}
