package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* 
* @author MRC 
* @date 2019年4月24日 下午4:11:54 
* @version 1.0 
*/
@Controller
public class DemoController {

	
	@RequestMapping("/")
	public String index(){
		
		return "index";
		
	}
	
	@RequestMapping("/hello")
	public String hello(){
		
		return "hello";
		
	}
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
	@RequestMapping("/list")
	public String list() {
		
		return "list";
		
	}
	
	
	
}
