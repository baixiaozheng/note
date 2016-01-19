package com.note.passport.controller;

import java.io.IOException;
import java.util.Date;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.model.user.User;

public class TestJson {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		User user = new User();
		user.setId(1);
		user.setEmail("aaa@163.com");
		user.setEnable(true);
		user.setGithub("");
		user.setMobile("18888888888");
		user.setNickname("昵称");
		user.setPassword("password");
		user.setRealName("张三");
		user.setQq("100000");
		String s = jsontest(user, 0);
		JSONObject jsonObj = new JSONObject(s);
		JSONObject u = jsonObj.getJSONObject("user");
		ObjectMapper mapper = new ObjectMapper();  
		User user2 = mapper.readValue(u.toString(), User.class);
		System.out.println(user2.getMobile());
		
	}

	private static String jsontest(User user, int key) {
		String result = "";
		switch (key) {
		case 1:
			result = "{\"error\":true,\"errorInfo\":\"Token can not be empty!\"}";
			break;
		case 2:
			result = "{\"error\":true,\"errorInfo\":\"Token is not found!\"}";
			break;
		case 0:
			JSONObject userjson = new JSONObject(user);
			result = "{\"error\":true,\"errorInfo\":\"Token is not found!\",\"user\":" + userjson.toString() + "}";
			break;
		default:
			result = "default";
			break;
			
		}
		return result;
	}
}
