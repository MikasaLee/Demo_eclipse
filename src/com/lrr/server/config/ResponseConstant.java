package com.lrr.server.config;

public class ResponseConstant {

	// 访问成功
	public static int RESULT_SUCCESS = 0x01;
	// 访问失败
	public static int RESULT_FAILURE = 0x02;
	// 参数错误
	public static int PARAMS_ERROR = 0x03;
	// 用户未注册
	public static int NO_REGIST_ERROR = 0x04;
	// 用户名重复
	public static int USERNAME_ERROR = 0x05;

	// 访问成功信息
	public static String SUCCESS_MSG = "obtain message success";
	// 访问失败信息
	public static String FAILURE_MSG = "obtain message failrue";
	// 参数错误信息
	public static String PARAMS_MSG = "parameter is error";

}
