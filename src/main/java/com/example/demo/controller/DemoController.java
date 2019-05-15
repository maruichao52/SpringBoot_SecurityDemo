package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.User;

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
	
	@RequestMapping("look")
	@ResponseBody
	@PreAuthorize("hasRole('TEST')")
	public String look(){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
		username = ((UserDetails)principal).getUsername();
		} else {
		username = principal.toString();
		}
		
		return username;
	}
	
	
}
