package com.fank243.cloud.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.cloud.component.domain.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统角色
 * 
 * @author FanWeiJie
 * @date 2021-03-24 20:40:32
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select a.id,a.code,a.`name` from tb_sys_role a left join tb_sys_role_permission b on a.id = b.role_id where `status` = 0 and b.permission_id = #{permissionId}")
    List<SysRole> findByRoleId(Long permissionId);

    @Select("select a.id,a.`code` from tb_sys_role a left join tb_sys_user_role b on a.id = b.role_id where `status` = 0 and b.user_id = #{userId}")
    List<SysRole> findByUserId(Long userId);
}
