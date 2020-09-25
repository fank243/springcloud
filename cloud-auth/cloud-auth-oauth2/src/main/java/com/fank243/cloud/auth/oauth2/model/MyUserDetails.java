package com.fank243.cloud.auth.oauth2.model;

import com.fank243.cloud.component.domain.dto.CurrUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 用户实体
 * 
 * @author FanWeiJie
 * @date 2020-09-21 17:40:15
 */
@Data
public class MyUserDetails implements UserDetails {

    private final CurrUser currUser;

    public MyUserDetails(CurrUser currUser) {
        this.currUser = currUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> permList = currUser.getPermList();
        return AuthorityUtils.createAuthorityList(permList.toArray(new String[permList.size()]));
    }

    @Override
    public String getPassword() {
        return currUser.getPassword();
    }

    @Override
    public String getUsername() {
        return currUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 账户是否未过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 账户是否未被锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 密码是否未过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 账号是否已启用
        return true;
    }
}
