package com.fank243.cloud.gateway.admin.repository;

import com.fank243.cloud.gateway.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户
 * 
 * @author FanWeiJie
 * @date 2020-09-17 17:04:52
 */
@Repository
public interface SysUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
