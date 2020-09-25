package com.fank243.cloud.gateway.admin.repository;

import com.fank243.cloud.component.domain.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 管理员
 * 
 * @author FanWeiJie
 * @date 2020-09-24 17:37:09
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

}
