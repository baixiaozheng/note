package com.note.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.management.security.annotation.Authority;
import com.note.management.security.common.AuthorityType;
import com.note.management.security.util.SecurityUtil;
import com.note.model.user.User;

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
		User user = SecurityUtil.currentLogin(request, response);
		System.out.println(user.getMobile());
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value = "no")
	@Authority(type = AuthorityType.ANYMOUS)
	public String no(){
		return "no";
	}
}
 