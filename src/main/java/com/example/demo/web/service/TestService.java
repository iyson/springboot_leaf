package com.example.demo.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.web.mapper.TestMapper;


/**
* main화면 Service
*/
@Service
public class TestService {

	@Autowired 
	private TestMapper testMapper;

	public List<Map<String, Object>>  getTestInfo() {
		return testMapper.getTestInfo();
	}


	
}

