package com.lrr.server.entity;

import com.lrr.server.config.ResponseConstant;

public class ResponseResult {

	// 服务器返回状态代码
	private int code = ResponseConstant.RESULT_FAILURE;
	// 服务器返回信息
	private String message = ResponseConstant.FAILURE_MSG;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
