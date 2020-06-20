package com.lrr.server.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lrr.server.config.ResponseConstant;
import com.lrr.server.entity.ResponseResult;
import com.lrr.server.entity.StudentBean;
import com.lrr.server.entity.TeacherBean;
import com.lrr.server.entity.TeacherBean;
import com.lrr.server.entity.AnswerBean;
import com.lrr.server.entity.GroupMessageBean;
import com.lrr.server.entity.InformationBean;
import com.lrr.server.entity.QuestionBean;
import com.lrr.server.service.AnswerService;
import com.lrr.server.service.GMService;
import com.lrr.server.service.InformationService;
import com.lrr.server.service.QuestionService;
import com.lrr.server.service.STGroupService;
import com.lrr.server.service.TeacherService;
import com.lrr.server.service.TeacherService;
import com.lrr.server.utils.EncodeUtil;
import com.lrr.server.utils.Md5Util;

@Controller
public class TeacherController {

	//得到学生
	@Autowired
	private TeacherService TeacherService;
	@RequestMapping(value = "/getTea.json", method = RequestMethod.GET)
	public @ResponseBody TeacherBean gettea(
			@RequestParam(value = "teaId", required = true) String teaId) {
		if (StringUtils.isEmpty(teaId)) {
			return null;
		}		
		
		TeacherBean TeacherBean = TeacherService.getTeaById(teaId);
//		System.out.println(TeacherBean.getCollegeName());
		return TeacherBean;
	}
	
	
	//得到群消息
	@Autowired
	private GMService gmService;
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value = "/getGruopMessageByTea.json", method = RequestMethod.GET)
	public @ResponseBody GroupMessageBean getGruopMessage(
			@RequestParam(value = "teaId", required = true) String teaId) {
		
		GroupMessageBean groupMessageBean = gmService.getGM(teaId);
		
		return groupMessageBean;
	}
	
	//发布群消息
	@Autowired
	private GMService gMService;
	
	@RequestMapping(value = "/putGroupMessage.json", method = RequestMethod.GET)
	public @ResponseBody ResponseResult putGroupMessage(
			@RequestParam(value = "teaId", required = true) String teaId,
			@RequestParam(value = "gMContent", required = true) String gMContent) {
			ResponseResult result = new ResponseResult();
			if(gmService.updateGM(teaId, gMContent) == 1){
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
			}else{
				result.setCode(ResponseConstant.RESULT_FAILURE);
			}

			return result;
		
	}
	
	//得到负责的所有学生的所有问题
	@Autowired
	private STGroupService sTGroupService;
	@Autowired
	private QuestionService questionService;
	@RequestMapping(value = "/getAllQuesByTeaId.json", method = RequestMethod.GET)
	public @ResponseBody List<QuestionBean> getAllQuesByTeaId(
			@RequestParam(value = "teaId", required = true) String teaId) {

		if(teaId == null) return null;
		
		//得到所有负责的学生
		List<StudentBean> studentBeans =sTGroupService.findStudentsByTeaId(teaId);
		
		//得到所有问题
		List<QuestionBean> questionBeans = new ArrayList<QuestionBean>();
		for(StudentBean studentBean:studentBeans){
			questionBeans.addAll(questionService.getAllQues(studentBean.getStuId()));
		}
	
		return questionBeans;
		
	}
	
	//得到所有自己的回答
	
	@Autowired
	private AnswerService answerService;
	@RequestMapping(value = "/getAllAnsByTeaId.json", method = RequestMethod.GET)
	public @ResponseBody List<AnswerBean> getAllAnsByTeaId(
			@RequestParam(value = "teaId", required = true) String teaId) {

		if(teaId == null) return null;
		
		//得到所有负责的学生
		List<StudentBean> studentBeans =sTGroupService.findStudentsByTeaId(teaId);
		
		//得到所有问题
		List<QuestionBean> questionBeans = new ArrayList<QuestionBean>();
		for(StudentBean studentBean:studentBeans){
			questionBeans.addAll(questionService.getAllQues(studentBean.getStuId()));
		}
	
		
		List<AnswerBean> answerBeans = new ArrayList<AnswerBean>();
		//得到所有回答
		for(QuestionBean questionBean:questionBeans){
			AnswerBean answerBean = answerService.getAnsByQuesId(questionBean.getQuesId());
			answerBeans.add(answerBean);
		}
		return answerBeans;
		
	}
	
	//提交所有自己的回答	//gai
	@Autowired
	private AnswerService AnswerService1;
	
	@RequestMapping(value = "/putAns.json", method = RequestMethod.GET)
	public @ResponseBody ResponseResult putAns(
			@RequestParam(value = "queId", required = true) String queId,
			@RequestParam(value = "teaContent", required = true) String teaContent) {
			ResponseResult result = new ResponseResult();
			
			if(answerService.updateAnsByQuesId(Integer.parseInt(queId), teaContent) != 1){
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
			}else{
				result.setCode(ResponseConstant.RESULT_FAILURE);
			}
			
			return result;
		
	}
}
