package com.fank243.cloud.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.cloud.component.domain.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录接口
 * 
 * @author FanWeiJie
 * @date 2021-03-21 18:43:02
 */
@Mapper
public interface LoginMapper  extends BaseMapper<SysUser> {

}
