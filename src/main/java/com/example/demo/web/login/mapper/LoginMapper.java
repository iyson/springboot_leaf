package com.example.demo.web.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.web.login.vo.UserVo;

@Mapper
public interface LoginMapper {

	public UserVo getUserInfo(String username);

}
