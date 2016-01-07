package com.note.web.controller.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class Test {
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "setCookie")
	public void setCookie(HttpServletResponse response){
		addCookie(response, "testCookie", "test", -1);
		System.out.println("set over");
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "getCookie1")
	public String getCookie1(HttpServletRequest request){
		Cookie cookie = getCookieByName(request, "testCookie");
		System.out.println("1"+cookie.getValue());
		return "1"+cookie.getValue();
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "getCookie2")
	public String getCookie2(HttpServletRequest request){
		Cookie cookie = getCookieByName(request, "testCookie");
		System.out.println("2"+cookie.getValue());
		return "2"+cookie.getValue();
	}
	
	public void addCookie(HttpServletResponse response,String name,String value,int maxAge){
	    Cookie cookie = new Cookie(name,value);
	    cookie.setPath("/");
	    if(maxAge>0)  cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	}
	
	public Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	private Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
}
