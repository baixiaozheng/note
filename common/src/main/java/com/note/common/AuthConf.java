package com.note.common;

/**
 * 配置
 * 
 * @author baixiaozheng
 * @Date 2016年1月10日 下午1:02:27
 */
public class AuthConf {

	public static final int TOKEN_TIMEOUT = 1800; // token的cookie的有效期，单位为秒
	
	public static final String COOKIE_NAME = "token"; // token的cookie的名字
	
	public static final String SECRET_KEY = "3dqYp97xiBni5geNpvcCW293"; // des key
	
	public static final String SSO_SERVICE = "http://192.168.0.11:8888/auth/"; //认证系统url
}
