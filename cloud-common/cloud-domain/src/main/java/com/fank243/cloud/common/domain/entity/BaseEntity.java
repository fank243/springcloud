package com.fank243.cloud.common.domain.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类基类,JPA审计
 * 
 * @author FanWeiJie
 * @date 2020-09-19 18:49:04
 */
@Data
public abstract class BaseEntity implements Serializable {

    @JSONField(serialize = false)
    private Long createdBy;

    @JSONField(serialize = false)
    private Date createdDate;

    @JSONField(serialize = false)
    private Long lastModifiedBy;

    @JSONField(serialize = false)
    private Date lastModifiedDate;
}
