package com.note.web.controller.login;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.note.common.HTTPCodeStatus;
import com.note.model.user.User;
import com.note.service.user.UserServices;
import com.note.util.request.RequestUtil;
import com.note.util.validate.ValidateUtil;
import com.note.web.controller.BaseController;
import com.note.web.entity.ResponseEntity;
import com.note.web.security.annotation.Authority;
import com.note.web.security.common.AuthorityType;
import com.note.web.security.util.SecurityUtil;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController {

	@Resource(name = "userService")
	private UserServices userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	@Deprecated
	public ResponseEntity login(String username, String password) throws IOException {
		User user = null;
		if (ValidateUtil.isMail(username)) {
			user = userService.getUserByEmail(username);
		} else if (ValidateUtil.isMobile(username)) {
			user = userService.getUserByMobile(username);
		}
		// pwd是请求来的用户密码的MD5摘要
		if (user == null || password == null || "".equals(password) || !password.equals(user.getPassword())) {
			return returnFailed(HTTPCodeStatus.HTTPCODE_LOGIN_ERROR,  HTTPCodeStatus.HTTPCODE_LOGIN_ERROR_MESSAGE);
		}
		if (!user.getEnable()) {
			return returnFailed(HTTPCodeStatus.HTTPCODE_LOGIN_ERROR,  HTTPCodeStatus.HTTPCODE_LOGIN_ERROR_MESSAGE);
		}

		userService.updateUserIpAndLastestLoginTime(user, RequestUtil.getRemoteIPAddress(getRequest()));
		SecurityUtil.setSecurityUser(user);
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, user, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}
	

	@RequestMapping(value="loginSuccess")
	@ResponseBody
	public ResponseEntity loginSuccess(HttpServletRequest request) {
		User user = SecurityUtil.currentLogin(request,response);
		user = userService.getById(user.getId());
		user.setLastestLoginTime(new Date());
		user.setLastestLoginIp(RequestUtil.getRemoteIPAddress(request));
		userService.update(user);
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, user, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}

	@RequestMapping(value="loginFailed")
	public String loginFailed() {
		System.out.println("登录失败");
		return "redirect:logout"; 
	}
	
	@RequestMapping(value="getNickname")
	@ResponseBody
	@Authority(type = AuthorityType.SECURITY)
	public ResponseEntity getNickname(){
		User user = SecurityUtil.currentLogin(request,response);
		return returnSuccess(user.getNickname());
	}
}
