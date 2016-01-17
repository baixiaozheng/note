package com.note.passport.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.note.common.AuthConf;
import com.note.model.user.User;
import com.note.service.user.UserServices;
import com.note.util.des.DESUtils;
import com.note.util.md5.MD5Util;
import com.note.util.validate.ValidateUtil;

@Controller
public class AuthController {

	@Resource(name = "redisTemplate")
	private RedisTemplate<String, User> redisTemplate;
	
	private ValueOperations<String, User> userOperations;
	
	@Resource(name="userService")
	private UserServices userService;

	@RequestMapping(value = "preLogin")
	public void preLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = getCookieByName(request, AuthConf.COOKIE_NAME);
		if(cookie == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			String encodedToken = cookie.getValue();
			String decodedToken = DESUtils.decrypt(encodedToken, AuthConf.SECRET_KEY);
			userOperations = redisTemplate.opsForValue();
			User user = userOperations.get(decodedToken);
			if (user != null) {// 判断token是否存在
				String setCookieURL = request.getParameter("setCookieURL");
				String gotoURL = request.getParameter("gotoURL");
				if(setCookieURL != null)
	                response.sendRedirect(setCookieURL + "?token=" + encodedToken + "&expiry=" + cookie.getMaxAge() + "&gotoURL=" + gotoURL);
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}
	
	@RequestMapping(value = "authToken")
	public void authToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder result = new StringBuilder("{");
		PrintWriter out = response.getWriter();
		String encodedToken = request.getParameter("cookieName");
		if(encodedToken == null) {
			result.append("\"error\":true,\"errorInfo\":\"Token can not be empty!\"");
		} else {
			String decodedToken = DESUtils.decrypt(encodedToken, AuthConf.SECRET_KEY);
			userOperations = redisTemplate.opsForValue();
			User user = userOperations.get(decodedToken);
			if (user != null) {// 判断token是否存在
				result.append("\"error\":false,\"username\":").append(user.getRealName());
			}
			else {
				result.append("\"error\":true,\"errorInfo\":\"Token is not found!\"");
			}
		}
		result.append("}");
		out.print(result);
	}
	
	@RequestMapping(value = "doLogout")
	public void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder result = new StringBuilder("{");
		PrintWriter out = response.getWriter();
		String encodedToken = request.getParameter("cookieName");
		if(encodedToken == null) {
			result.append("\"error\":true,\"errorInfo\":\"Token can not be empty!\"");
		} else {
			String decodedToken = DESUtils.decrypt(encodedToken, AuthConf.SECRET_KEY);
			userOperations = redisTemplate.opsForValue();
			userOperations.getOperations().delete(decodedToken);
			result.append("\"error\":false");
		}
		result.append("}");
		out.print(result);
	}

	@RequestMapping(value = "doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;
		if (ValidateUtil.isMail(username)) {
			user = userService.getUserByEmail(username);
		} else if (ValidateUtil.isMobile(username)) {
			user = userService.getUserByMobile(username);
		}
		// pwd是请求来的用户密码的MD5摘要
		if (user == null || password == null || "".equals(password) || !MD5Util.digest(password).equals(user.getPassword()) || !user.getEnable()) {
			request.getRequestDispatcher("login.jsp?errorInfo=username or password is wrong!").forward(request, response);
		} else {
			String token = generateStrRecaptcha(16);
			String encodedToken = DESUtils.encrypt(token, AuthConf.SECRET_KEY);
			userOperations = redisTemplate.opsForValue();
			userOperations.set(token, user, 30, TimeUnit.MINUTES);
			addCookie(response, AuthConf.COOKIE_NAME, encodedToken, AuthConf.TOKEN_TIMEOUT);

			String setCookieURL = request.getParameter("setCookieURL");
			String gotoURL = request.getParameter("gotoURL");
			
			PrintWriter out = response.getWriter();
			out.print("<script type='text/javascript'>");
			out.print("document.write(\"<form id='url' method='post' action='" + setCookieURL + "'>\");");
			out.print("document.write(\"<input type='hidden' name='gotoURL' value='" + gotoURL + "' />\");");
			out.print("document.write(\"<input type='hidden' name='token' value='" + encodedToken + "' />\");");
			out.print("document.write(\"<input type='hidden' name='expiry' value='" + AuthConf.TOKEN_TIMEOUT + "' />\");");
			out.print("document.write('</form>');");
			out.print("document.getElementById('url').submit();");
			out.print("</script>");
		}
	}	
	
	

	/**
	 * 生成随机字符串(含大小写数字)
	 */
	public static String generateStrRecaptcha(int length) {
		Random r = new Random(System.currentTimeMillis());

		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = r.nextInt(3);// 大写，小写，数字
			long result = 0;
			switch (number) {
			case 0:
				result = Math.round(Math.random() * 25 + 65);
				sf.append(String.valueOf((char) result));
				break;
			case 1:
				result = Math.round(Math.random() * 25 + 97);
				sf.append(String.valueOf((char) result));
				break;
			case 2:
				sf.append(String.valueOf(new Random().nextInt(10)));
				break;

			}
		}
		return sf.toString();
	}

	public void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	public Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	private Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
