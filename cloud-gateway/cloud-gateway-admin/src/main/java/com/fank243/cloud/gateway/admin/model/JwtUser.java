package com.fank243.cloud.gateway.admin.model;

import com.fank243.cloud.gateway.admin.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * JWT 用户对象
 * 
 * @author FanWeiJie
 * @date 2020-09-18 17:50:04
 */
public class JwtUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {}

    /**
     * 通过 user 对象创建jwtUser
     */
    public JwtUser(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        enabled = user.getEnabled() == null || user.getEnabled();
//        authorities = user.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String toString() {
        return "JwtUser{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\''
            + ", authorities=" + authorities + '}';
    }

}
