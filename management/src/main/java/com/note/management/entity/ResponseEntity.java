package com.note.management.entity;

import java.io.Serializable;

import com.note.common.HTTPCodeStatus;

@SuppressWarnings("serial")
public class ResponseEntity<T> implements Serializable {

	public ResponseEntity() {

	}

	public ResponseEntity(int code) {

		this.code = code;
	}

	public ResponseEntity(Object data) {

		this.data = data;
	}

	public ResponseEntity(int code, String message) {

		this.code = code;
		this.message = message;
	}

	public ResponseEntity(int code, Object data, String message) {

		this.code = code;
		this.data = data;
		this.message = message;
	}

	/**
	 * http返回结果状态码
	 */
	private int code = HTTPCodeStatus.HTTPCODE_OK;

	/**
	 * 返回的数据内容 <br/>
	 * 有如下4种数据格式：<br/>
	 * 单值："value";对象：{id,...}；列表：[{},{},...],分页对象Pagination：{total:100,list:[{},
	 * {},...]}
	 */
	private Object data;

	/**
	 * 错误消息
	 */
	private String message;

	public int getCode() {

		return code;
	}

	public void setCode(int code) {

		this.code = code;
	}

	public Object getData() {

		return data;
	}

	public void setData(Object data) {

		this.data = data;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

}
