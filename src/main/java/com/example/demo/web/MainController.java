package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.web.service.TestService;

@Controller
public class MainController {

	@Autowired
	TestService testService;
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		
		//LoginVo result = mainService.getLoginInfo("user1");
		
		return mav;
	}	
	
	
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		
		//LoginVo result = mainService.getLoginInfo("user1");
		
		return mav;
	}
	

}
