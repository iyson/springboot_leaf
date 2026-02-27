package com.example.demo.web.login.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.web.login.vo.UserVo;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {
	
    private final UserVo user;

    public CustomUserDetails(UserVo user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 설정 (테이블의 grade_seq 등에 따라 설정 가능)
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() { return user.getPassword(); }

    @Override
    public String getUsername() { return user.getUserId(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        // activate_yn이 'Y'이고 status가 'Y'인 경우만 활성화
        return "Y".equals(user.getActivateYn()) && "Y".equals(user.getStatus());
    }
}