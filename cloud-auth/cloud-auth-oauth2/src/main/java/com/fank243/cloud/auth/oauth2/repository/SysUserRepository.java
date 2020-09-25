package com.fank243.cloud.auth.oauth2.repository;

import com.fank243.cloud.component.domain.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 管理员
 * 
 * @author FanWeiJie
 * @date 2020-09-25 14:42:16
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);
}
