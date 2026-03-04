package com.example.demo.web.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.web.login.mapper.LoginMapper;
import com.example.demo.web.login.vo.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserVo user = loginMapper.getUserInfo(username);
    	if (user == null) throw new UsernameNotFoundException("사용자 없음");
        log.info("DB에서 가져온 암호 확인: [{}]", user.getPassword());
        return new CustomUserDetails(user);
    }
}