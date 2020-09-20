package com.fank243.cloud.component.domain.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类基类,JPA审计
 * 
 * @author FanWeiJie
 * @date 2020-09-19 18:49:04
 */
@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @JSONField(serialize = false)
    @CreatedBy
    @Column(updatable = false, columnDefinition = "BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建人'")
    private Long createdBy;

    @JSONField(serialize = false)
    @CreatedDate
    @Column(updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createdDate;

    @JSONField(serialize = false)
    @LastModifiedBy
    @Column(nullable = false, columnDefinition = "BIGINT(20) NOT NULL DEFAULT 0 COMMENT '修改人'")
    private Long lastModifiedBy;

    @JSONField(serialize = false)
    @LastModifiedDate
    @Column(updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private Date lastModifiedDate;
}
