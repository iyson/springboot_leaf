package com.example.demo.web.login.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.web.login.mapper.LoginMapper;
import com.example.demo.web.login.vo.UserVo;


/**
* main화면 Service
*/
@Service
public class LoginService {

	@Autowired 
	private LoginMapper loginMapper;

	public UserVo  getUserInfo(String userId) {
		return loginMapper.getUserInfo(userId);
	}


	
}

