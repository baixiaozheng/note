package com.note.web.security.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.common.AuthConf;
import com.note.common.HTTPCodeStatus;
import com.note.model.user.User;
import com.note.util.cookie.CookieUtil;
import com.note.web.entity.ResponseEntity;
import com.note.web.security.annotation.Authority;
import com.note.web.security.common.AuthorityType;


/**
 * 权限控制类
 *
 * @date 2014年6月28日
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOG = LogFactory.getLog(AuthorityInterceptor.class);

	// @Resource(name = "messageSource")
	// private AbstractMessageSource messageSource;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
	 * preHandle (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 访问静态资源
		String uri = request.getRequestURI();
		if (uri.contains("/statics/") || uri.contains("/static/") || uri.contains("logout") || uri.contains("/download")
				|| uri.contains("/html") || uri.contains("/index.html") || uri.contains("/login")) {
			return true;
		}
		
		if (!(handler instanceof HandlerMethod)) {
		    return true;
		}
		HandlerMethod mHandler = (HandlerMethod) handler;
		Authority authority = mHandler.getMethodAnnotation(Authority.class);
		// not found annotation, allow every one
		if (null == authority) {
			return true;
		}
		// author type
		AuthorityType type = authority.type();
		if (AuthorityType.ANYMOUS.equals(type)) {
			return true;
		}
		
		String path = request.getContextPath();
		String gotoURL = request.getParameter("gotoURL");
		if (gotoURL == null) {
			gotoURL = request.getRequestURL().toString();
		}
		String URL = AuthConf.SSO_SERVICE + "preLogin?setCookieURL=" + request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort() + path + "/setCookie&gotoURL=" + gotoURL;
		Cookie cookie = CookieUtil.getCookieByName(request, AuthConf.COOKIE_NAME);
		if (cookie != null) {
			//authCookie(request, response, cookie, URL);
		} else {
			response.sendRedirect(URL);
			if (AuthorityType.SECURITY.equals(type)) {
				return false;
			} 
			return true;
		}
		
		
		//User user = SecurityUtil.currentLogin();
		User user = authCookie(request, response, cookie, URL);
		
		if (null == user) {
			return _reponseAuthenticationError(response);
		}
		return true;
	}
	private User authCookie(HttpServletRequest request, HttpServletResponse response, Cookie cookie,
			String URL) throws IOException, ServletException {
		User user = null;
		NameValuePair[] params = new NameValuePair[1];
		params[0] = new NameValuePair("cookieName", cookie.getValue());
		try {
			JSONObject result = post(request, response, params, "authToken");
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
			response.sendRedirect(URL);
			throw new RuntimeException(e);
		}
		return user;
	}

	private JSONObject post(HttpServletRequest request, HttpServletResponse response, 
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
	/**
	 * Print authentication error message
	 *
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private boolean _reponseAuthenticationError(HttpServletResponse response) {
		int errorCode = HTTPCodeStatus.HTTPCODE_USER_NOTLOGIN;
		String httpResponseMessage = HTTPCodeStatus.HTTPCODE_USER_NOTLOGIN_MESSAGE;
		LOG.info("response msg: " + httpResponseMessage);
		try {
			ResponseEntity re = new ResponseEntity(errorCode, null, httpResponseMessage);
			response.addHeader(errorCode + "", URLEncoder.encode(httpResponseMessage, "UTF-8"));
			response.addHeader("Content-Type", "application/json; charset=utf-8");
			String code = "\"code\" : " + "\"" + errorCode + "\"";
			String msg = "\"message\" : \"" + httpResponseMessage + "\"";
			String result = "{" + code + "," + msg + "}";
			LOG.info("result: " + result);
			response.getWriter().write(result);
			response.getWriter().flush();
		} catch (UnsupportedEncodingException e) {
			response.addHeader(errorCode + "", "security.user.error");
			LOG.error("response authentication error response exception ", e);
		} catch (IOException e) {
			LOG.error("response authentication error response exception ", e);
		}
		return false;
	}

}
