package com.note.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.management.security.annotation.Authority;
import com.note.management.security.common.AuthorityType;

@Controller
public class LoginController extends BaseController{

	@ResponseBody
	@RequestMapping(value = "tologin")
	@Authority(type = AuthorityType.SECURITY)
	public String login(){
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value = "index")
	@Authority(type = AuthorityType.SECURITY)
	public String index(){
		return "index";
	}
}
 