package com.fank243.springcloud.service.user.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_user_role")
public class SysUserRole extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private SysUser sysUser;

    @ManyToOne
    @JoinColumn
    private SysRole sysRole;

    public SysUserRole(SysUser sysUser, SysRole sysRole) {
        this.sysUser = sysUser;
        this.sysRole = sysRole;
    }
}
