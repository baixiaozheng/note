package com.note.management.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.note.common.Page;
import com.note.management.entity.ResponseEntity;

@SuppressWarnings("rawtypes")
public class BaseController {

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected ResponseEntity responseEntity;

	protected static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	protected Page page = new Page(10);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}

	/**
	 * 加载系统资源文件
	 * 
	 * @param properties
	 *            (资源文件名称)
	 * @return
	 */
	public ResourceBundle getProperties(String properties) {

		ResourceBundle bundle = ResourceBundle.getBundle(properties, Locale.getDefault());
		return bundle;
	}

//	/**
//	 * 基于@ExceptionHandler异常处理基类
//	 * 
//	 * @return
//	 * @throws IOException
//	 */
//	@ExceptionHandler(Exception.class)
//	public String exception(HttpServletRequest request, Exception exception) throws IOException {
//
//		ResourceBundle bundle = getProperties("error");
//		if (isAjaxRequest()) {
//			ResponseEntity responseEntity = new ResponseEntity(ResponseCode.SYS_ERROR, exception.getMessage(), exception.getMessage());
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("text/html");
//			response.getWriter().write(JSONUtil.beanToJson(responseEntity, true));
//			response.getWriter().flush();
//			response.getWriter().close();
//			return null;
//		}
//		else {
//			this.getRequest().setAttribute("isAjaxRequest", isAjaxRequest());
//			this.getRequest().setAttribute("message", exception.getMessage());
//			response.sendRedirect(bundle.getString("sys_error"));
//		}
//		return null;
//	}

	/**
	 * 请求正常，返回json数据
	 * 
	 * @param t
	 * @return
	 * @author mwang
	 * @Date 2015年6月16日
	 */
	protected final <T> ResponseEntity returnSuccess(final T t) {

		return new ResponseEntity(t);
	}

	/**
	 * 请求正常，返回json数据
	 * 
	 * @param t
	 * @return
	 * @author mwang
	 * @Date 2015年6月16日
	 */
	protected final <T> ResponseEntity returnSuccess(int code, final T t, String message) {

		return new ResponseEntity(code, t, message);
	}

	/**
	 * 请求正常，返回json数据
	 * 
	 * @param t
	 * @return
	 * @author mwang
	 * @Date 2015年6月16日
	 */
	protected final <T> ResponseEntity returnSuccess(int code, final T t) {

		return new ResponseEntity(code, t, null);
	}

	/**
	 * 参数验证失败，返回错误状态码和错误信息
	 * 
	 * @param code
	 *            http错误状态码
	 * @param bindingResult
	 *            参数验证结果对象
	 * @return
	 * @author mwang
	 * @Date 2015年6月19日
	 */
	public ResponseEntity returnFailed(int code, String message) {

		return new ResponseEntity(code, message);
	}

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {

		this.request = request;
		this.response = response;
	}

	protected boolean isAjaxRequest() {

		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else return false;
	}

	public HttpServletRequest getRequest() {

		return request;
	}

	public void setRequest(HttpServletRequest request) {

		this.request = request;
	}

	public HttpServletResponse getResponse() {

		return response;
	}

	public void setResponse(HttpServletResponse response) {

		this.response = response;
	}

	public ResponseEntity getResponseEntity() {

		return responseEntity;
	}

	public void setResponseEntity(ResponseEntity responseEntity) {

		this.responseEntity = responseEntity;
	}

	
}