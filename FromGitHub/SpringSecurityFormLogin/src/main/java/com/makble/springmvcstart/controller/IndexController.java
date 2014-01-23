package com.makble.springmvcstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.makble.springmvcstart.service.UserService;


@Controller
@RequestMapping( value = "/" )
public class IndexController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping( value = "/", method = RequestMethod.GET )
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("/view/homenav.jsp");
		return mav;
	}
	
	@RequestMapping( value = "/login", method = RequestMethod.GET )
	public String displayLogin() {
		
					
		return "/view/login.jsp";
	}
	
	@RequestMapping( value = "/login" , method = RequestMethod.POST)
	public String validateLogin( @RequestParam String username, @RequestParam String password ) {
		
		if( service.login(username,password)) {
			return "/view/success.jsp";
		} else {
			return "/view/error.jsp";
		}
	}
		
}