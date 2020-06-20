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
import com.lrr.server.entity.AnswerBean;
import com.lrr.server.entity.GroupMessageBean;
import com.lrr.server.entity.InformationBean;
import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.QuestionBean;
import com.lrr.server.service.AnswerService;
import com.lrr.server.service.GMService;
import com.lrr.server.service.InformationService;
import com.lrr.server.service.LoginStuService;
import com.lrr.server.service.QuestionService;
import com.lrr.server.service.StudentService;
import com.lrr.server.service.TeacherService;
import com.lrr.server.utils.EncodeUtil;
import com.lrr.server.utils.Md5Util;

@Controller
public class StudentController {

	//得到学生
	@Autowired
	private StudentService studentService;
	@RequestMapping(value = "/getStu.json", method = RequestMethod.GET)
	public @ResponseBody StudentBean getStu(
			@RequestParam(value = "stuId", required = true) String stuId) {
		if (StringUtils.isEmpty(stuId)) {
			return null;
		}		
		
		StudentBean studentBean = studentService.getStudent(stuId);
//		System.out.println(studentBean.getCollegeName());
		return studentBean;
	}
	
	//得到公告
	@Autowired
	private InformationService InformationService;
	@RequestMapping(value = "/getInfo.json", method = RequestMethod.GET)
	public @ResponseBody InformationBean getInfo() {
		return InformationService.getInfor();
	}
	
	
	//得到群消息
	@Autowired
	private GMService gmService;
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value = "/getGruopMessage.json", method = RequestMethod.GET)
	public @ResponseBody GroupMessageBean getGruopMessage(
			@RequestParam(value = "stuId", required = true) String stuId) {
		
		//得到学生对应的老师
		TeacherBean teacherBean = teacherService.getTeaByStuId(stuId);
		
		if(teacherBean == null) return null;
		//得到老师发布的群消息
		GroupMessageBean groupMessageBean = gmService.getGM(teacherBean.getTeaId());
		
		return groupMessageBean;
	}
	
	
	//得到所有提交的问题
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value = "/getAllQues.json", method = RequestMethod.GET)
	public @ResponseBody List<QuestionBean> getAllQues(
			@RequestParam(value = "stuId", required = true) String stuId) {
		if(stuId == null) return null;
		return questionService.getAllQues(stuId);
	}
	
	//得到老师关于每个问题的回答
	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionService questionService1;
	@RequestMapping(value = "/getAllAns.json", method = RequestMethod.GET)
	public @ResponseBody List<AnswerBean> getAllAns(
			@RequestParam(value = "stuId", required = true) String stuId) {

		if(stuId == null) return null;
		List<QuestionBean> questionBeans = questionService1.getAllQues(stuId);
		List<AnswerBean> answerBeans = new ArrayList<AnswerBean>();
	
		for(QuestionBean questionBean:questionBeans){
			AnswerBean answerBean = answerService.getAnsByQuesId(questionBean.getQuesId());
			answerBeans.add(answerBean);
		}
		return answerBeans;
		
	}
	
	//提交问题
	@RequestMapping(value = "/putQues.json", method = RequestMethod.GET)
	public @ResponseBody ResponseResult putQues(
			@RequestParam(value = "stuId", required = true) String stuId,
			@RequestParam(value = "stuContent", required = true) String stuContent) {
		ResponseResult result = new ResponseResult();
		if (StringUtils.isEmpty(stuId) || StringUtils.isEmpty(stuContent)) {
			result.setCode(ResponseConstant.PARAMS_ERROR);
			result.setMessage(ResponseConstant.PARAMS_MSG);
		}
		
		//提交问题
		int i = questionService.addQuestion(stuId, stuContent);
		//得到问题id
		QuestionBean questionBean = questionService.getQuestion(stuId, stuContent);
		
		//默认回答
		int j = answerService.addAnswer(questionBean.getQuesId(), "[暂无回答]");

		
		if ((i == 1) && (j == 1)) {
			result.setCode(ResponseConstant.RESULT_SUCCESS);
			result.setMessage(ResponseConstant.SUCCESS_MSG);
		}else{
			result.setCode(ResponseConstant.RESULT_FAILURE);
		}
		return result;
	}
}
