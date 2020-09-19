package com.fank243.cloud.service.user.repository;

import com.fank243.cloud.service.user.domain.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 管理员
 * 
 * @author FanWeiJie
 * @date 2020-09-19 20:19:33
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    /**
     * 根据用户名查找
     * 
     * @param username 用户名
     * @return SysUser
     */
    SysUser findByUsername(String username);
}
