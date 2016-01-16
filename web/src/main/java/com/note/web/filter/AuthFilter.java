package com.note.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import com.note.common.AuthConf;


/**
 * Servlet Filter implementation class AuthFilter
 */
//@WebFilter(filterName="/AuthFilter",urlPatterns="/*")
public class AuthFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String path = request.getContextPath();
		String gotoURL = request.getParameter("gotoURL");
		if (gotoURL == null) {
			gotoURL = request.getRequestURL().toString();
		}
		String URL = AuthConf.SSO_SERVICE + "preLogin?setCookieURL=" + request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort() + path + "/setCookie&gotoURL=" + gotoURL;

		Cookie cookie = getCookieByName(request, AuthConf.COOKIE_NAME);
		if (request.getRequestURI().equals(path + "/logout")) {
			doLogout(request, response, chain, cookie, URL);
		} else if (request.getRequestURI().equals(path + "/setCookie")) {
			setCookie(request, response);
		} else if (cookie != null) {
			authCookie(request, response, chain, cookie, URL);
		} else {
			response.sendRedirect(URL);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

	private void setCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie cookie = new Cookie(AuthConf.COOKIE_NAME, request.getParameter("token"));
		cookie.setPath("/");
		cookie.setMaxAge(Integer.parseInt(request.getParameter("expiry")));
		response.addCookie(cookie);

		String gotoURL = request.getParameter("gotoURL");
		if (gotoURL != null){
			response.sendRedirect(gotoURL);
		}
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Cookie cookie,
			String URL) throws IOException, ServletException {
		NameValuePair[] params = new NameValuePair[1];
		params[0] = new NameValuePair("cookieName", cookie.getValue());
		try {
			post(request, response, chain, params, "doLogout");
		} catch (JSONException e) {
			throw new RuntimeException(e);
		} finally {
			response.sendRedirect(URL);
		}
	}

	private void authCookie(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Cookie cookie,
			String URL) throws IOException, ServletException {
		NameValuePair[] params = new NameValuePair[1];
		params[0] = new NameValuePair("cookieName", cookie.getValue());
		try {
			JSONObject result = post(request, response, chain, params, "authToken");
			if (result.getBoolean("error")) {
				response.sendRedirect(URL);
			} else {
				request.setAttribute("username", result.getString("username"));
				chain.doFilter(request, response);
			}
		} catch (JSONException e) {
			response.sendRedirect(URL);
			throw new RuntimeException(e);
		}
	}

	private JSONObject post(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
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
