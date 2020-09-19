package com.fank243.cloud.component.domain.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 数据传输对象:系统用户
 * 
 * @author FanWeiJie
 * @date 2020-09-19 19:04:47
 */
@Data
@Entity
public class SysUserDTO {
    @Id
    private Long id;

    private String username;
}
