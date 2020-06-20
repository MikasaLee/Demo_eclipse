package com.lrr.server.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lrr.server.config.ResponseConstant;
import com.lrr.server.entity.ResponseResult;
import com.lrr.server.entity.LoginRoot;
import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.LoginTea;
import com.lrr.server.service.LoginRootService;
import com.lrr.server.service.LoginStuService;
import com.lrr.server.service.LoginTeaService;

@Controller
public class LoginController {

	@Autowired
	private LoginStuService loginStuService;
	@RequestMapping(value = "/loginStu.json", method = RequestMethod.GET)
	public @ResponseBody ResponseResult loginStu(
			@RequestParam(value = "stuId", required = true) String stuId,
			@RequestParam(value = "password", required = true) String password) {
		ResponseResult result = new ResponseResult();
		if (StringUtils.isEmpty(stuId) || StringUtils.isEmpty(password)) {
			result.setCode(ResponseConstant.PARAMS_ERROR);
			result.setMessage(ResponseConstant.PARAMS_MSG);
		}
		
//		System.out.println("\n\n\n\n"+stuId+" "+password+"\taaaaaaa"+"\n\n\n\n\n");
		
		
		LoginStu loginStu = loginStuService.getLoginStu(stuId, password);

		
//		version:use md5
//
//		if (loginStu != null) {
//			if (loginStu.getPassword().equals(Md5Util.md5Encryption(password))) {
//				result.setCode(ResponseConstant.RESULT_SUCCESS);
//				result.setMessage(ResponseConstant.SUCCESS_MSG);
//			} else {
//				result.setMessage("用户名或密码错误");
//			}
//		} else {
//			result.setCode(ResponseConstant.NO_REGIST_ERROR);
//			result.setMessage("没有此用户信息");
//		}
		
//		version: no md5
		if (loginStu != null) {
			result.setCode(ResponseConstant.RESULT_SUCCESS);
			result.setMessage(ResponseConstant.SUCCESS_MSG);
		}else{
			result.setCode(ResponseConstant.RESULT_FAILURE);
		}
		return result;
	}
	
	
	//老师登录
	@Autowired
	private LoginTeaService loginTeaService;
	@RequestMapping(value = "/loginTea.json", method = RequestMethod.GET)
	public @ResponseBody ResponseResult loginTea(
			@RequestParam(value = "teaId", required = true) String teaId,
			@RequestParam(value = "password", required = true) String password) {
		ResponseResult result = new ResponseResult();
		if (StringUtils.isEmpty(teaId) || StringUtils.isEmpty(password)) {
			result.setCode(ResponseConstant.PARAMS_ERROR);
			result.setMessage(ResponseConstant.PARAMS_MSG);
		}
	
		LoginTea loginTea = loginTeaService.getLoginTea(teaId, password);

		if (loginTea != null) {
			result.setCode(ResponseConstant.RESULT_SUCCESS);
			result.setMessage(ResponseConstant.SUCCESS_MSG);
		}else{
			result.setCode(ResponseConstant.RESULT_FAILURE);
		}
		return result;
	}
	
	//管理员登录
	@Autowired
	private LoginRootService loginRootService;
	@RequestMapping(value = "/loginRoot.json", method = RequestMethod.GET)
	public @ResponseBody ResponseResult loginRoot(
			@RequestParam(value = "rootId", required = true) String rootId,
			@RequestParam(value = "password", required = true) String password) {
		ResponseResult result = new ResponseResult();
		if (StringUtils.isEmpty(rootId) || StringUtils.isEmpty(password)) {
			result.setCode(ResponseConstant.PARAMS_ERROR);
			result.setMessage(ResponseConstant.PARAMS_MSG);
		}
		LoginRoot loginRoot = loginRootService.getLoginRoot(rootId, password);

		if (loginRoot != null) {
			result.setCode(ResponseConstant.RESULT_SUCCESS);
			result.setMessage(ResponseConstant.SUCCESS_MSG);
		}else{
			result.setCode(ResponseConstant.RESULT_FAILURE);
		}
		return result;
	}
}
