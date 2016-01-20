package com.note.web.security.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.common.AuthConf;
import com.note.model.user.User;
import com.note.util.cookie.CookieUtil;
import com.note.web.security.service.entity.SecurityUser;


/**
 * 当前登陆用户
 */
public class SecurityUtil {

    /**
     * 获取登陆用户
     */
//    public static User currentLogin() {
//        try {
//            SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
//                    .getPrincipal();
//            return userDetails.getUser();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static void setSecurityUser(User user) {
//        try {
//            SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
//                    .getPrincipal();
//            userDetails.setUser(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
	public static User currentLogin(HttpServletRequest request, HttpServletResponse response) {
		User user = null;
		String path = request.getContextPath();
		String gotoURL = request.getParameter("gotoURL");
		if (gotoURL == null) {
			gotoURL = request.getRequestURL().toString();
		}
		String URL = AuthConf.SSO_SERVICE + "preLogin?setCookieURL=" + request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort() + path + "/setCookie&gotoURL=" + gotoURL;
		Cookie cookie = CookieUtil.getCookieByName(request, AuthConf.COOKIE_NAME);
		if (cookie != null) {
			NameValuePair[] params = new NameValuePair[1];
			params[0] = new NameValuePair("cookieName", cookie.getValue());
			try {
				JSONObject result = null;
				try {
					result = post(request, response, params, "authToken");
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (result.getBoolean("error")) {
					response.sendRedirect(URL);
				} else {
					JSONObject u = result.getJSONObject("user");
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");  
					ObjectMapper mapper = new ObjectMapper();
					mapper.setDateFormat(fmt);
					user = mapper.readValue(u.toString(), User.class);
				}
			} catch (JSONException e) {
				try {
					response.sendRedirect(URL);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return user;
		} else {
			
			return null;
		}
        
    }
	private static JSONObject post(HttpServletRequest request, HttpServletResponse response, 
			NameValuePair[] params, String method) throws IOException, ServletException, JSONException {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(AuthConf.SSO_SERVICE + method);
		postMethod.addParameters(params);
		switch (httpClient.executeMethod(postMethod)) {
		case HttpStatus.SC_OK:
			return new JSONObject(postMethod.getResponseBodyAsString());
		default:
			// 其它处理
			return null;
		}
	}
    public static void setSecurityUser(User user) {
        try {
            SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            userDetails.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

}
