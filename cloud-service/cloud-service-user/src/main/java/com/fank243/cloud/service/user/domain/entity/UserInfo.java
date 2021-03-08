package com.fank243.cloud.service.user.domain.entity;

import com.fank243.cloud.component.domain.entity.BaseEntity;
import com.fank243.cloud.service.user.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息表
 * 
 * @author FanWeiJie
 * @date 2020-03-24 16:48:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfo extends BaseEntity {

    private Long id;

    private Long userId;

    private String mobile;

    private String realname;

    private String idCard;

    private Boolean isVerifyRealname;

    private String email;

    private Boolean isVerifyEmail;

    private Gender gender;

    private String wxNumber;

    private String alipayNumber;

    private String qq;

    private String weibo;

    private Integer source;

    private Integer deviceType;

    private String iosDeviceNumber;

    private String androidDeviceNumber;

    private String deviceToken;

    private String addr;

}
