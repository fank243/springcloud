package com.fank243.cloud.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.cloud.component.domain.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统用户
 * 
 * @author FanWeiJie
 * @date 2021-03-24 20:40:32
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("SELECT su.* FROM t_sys_user su LEFT JOIN t_sys_user_role sur ON su.id=sur.user_id WHERE sur.role_id = #{roleId}")
    List<SysUser> selectUserByRoleId(String roleId);
}
