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
import com.lrr.server.entity.LoginRoot;
import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.QuestionBean;
import com.lrr.server.service.AnswerService;
import com.lrr.server.service.GMService;
import com.lrr.server.service.InformationService;
import com.lrr.server.service.LoginRootService;
import com.lrr.server.service.LoginStuService;
import com.lrr.server.service.LoginTeaService;
import com.lrr.server.service.QuestionService;
import com.lrr.server.service.STGroupService;
import com.lrr.server.service.StudentService;
import com.lrr.server.service.TeacherService;
import com.lrr.server.utils.EncodeUtil;
import com.lrr.server.utils.Md5Util;

@Controller
public class RootController {

	
	
	//得到所有学生
	@Autowired
	private StudentService studentService;
	@RequestMapping(value = "/getStus.json", method = RequestMethod.GET)
	public @ResponseBody List<StudentBean> getStus() {
	
		List<StudentBean> studentBeans = studentService.getStudents();
//		System.out.println(studentBean.getCollegeName());
		return studentBeans;
	}
	
	//添加学生（学生表和学生登陆表都要添加）
	@Autowired
	private LoginStuService loginStuService;
	@RequestMapping(value = "/addStu.json", method = RequestMethod.GET)
	public @ResponseBody ResponseResult addStu(
			@RequestParam(value = "stuId", required = true) String stuId,
			@RequestParam(value = "stuName", required = true) String stuName,
			@RequestParam(value = "collegeName", required = true) String collegeName,
			@RequestParam(value = "stuClass", required = true) String stuClass,
			@RequestParam(value = "password", required = true) String password) {
		
		
		ResponseResult result = new ResponseResult();
		if (StringUtils.isEmpty(stuId)) {
			result.setCode(ResponseConstant.PARAMS_ERROR);
			result.setMessage(ResponseConstant.PARAMS_MSG);
		}
		int i = studentService.addStudent(new StudentBean(stuId, stuName, collegeName, stuClass));

		if ( i == 1) {
			int j = loginStuService.addLoginStu(stuId, password);
			if(j == 1){
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
				return result;
			}
		}
			
		result.setCode(ResponseConstant.RESULT_FAILURE);
		
		return result;
	}
	
	
	//修改学生（学生表和学生登陆表都要修改）
	@RequestMapping(value = "/updStu.json", method = RequestMethod.GET)
	public @ResponseBody ResponseResult updStu(
			@RequestParam(value = "stuId", required = true) String stuId,
			@RequestParam(value = "stuName", required = true) String stuName,
			@RequestParam(value = "collegeName", required = true) String collegeName,
			@RequestParam(value = "stuClass", required = true) String stuClass,
			@RequestParam(value = "password", required = true) String password) {
		
		
		ResponseResult result = new ResponseResult();
		if (StringUtils.isEmpty(stuId)) {
			result.setCode(ResponseConstant.PARAMS_ERROR);
			result.setMessage(ResponseConstant.PARAMS_MSG);
		}
		int i = studentService.updStudent(new StudentBean(stuId, stuName, collegeName, stuClass));

		if ( i == 1) {
			int j = loginStuService.updLoginStu(stuId, password);
			if(j == 1){
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
				return result;
			}
		}
			
		result.setCode(ResponseConstant.RESULT_FAILURE);
		
		return result;
	}
	
	//删除学生（学生表和学生登陆表都要删除）
	/**
	 * 先删Answer表中的相关数据
	 * 在删Question表中的相关数据
	 * 删STGroup表中的相关数据
	 * 删LoginSTu表中的相关数据
	 * 删Student表中的相关数据
	 * 
	 * @param stuId
	 * @return
	 */
	
	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionService questionService;
	//绑定学生老师
	@Autowired
	private STGroupService sTGroupService;
	
	@RequestMapping(value = "/delStu.json", method = RequestMethod.GET)
	public @ResponseBody ResponseResult delStu(
			@RequestParam(value = "stuId", required = true) String stuId) {
		
		ResponseResult result = new ResponseResult();
		if (StringUtils.isEmpty(stuId)) {
			result.setCode(ResponseConstant.PARAMS_ERROR);
			result.setMessage(ResponseConstant.PARAMS_MSG);
		}

		//删除所有问题和答案
		List<QuestionBean> questionBeans = questionService.getAllQues(stuId);
		for(QuestionBean questionBean:questionBeans){
			answerService.delAnswer(questionBean.getQuesId());
		}
		questionService.delQuestion(stuId);
		sTGroupService.delBind(stuId);
		
		if ((loginStuService.delLoginStu(stuId) ==1)&&
		(studentService.delStudent(stuId) ==1)) {
			result.setCode(ResponseConstant.RESULT_SUCCESS);
			result.setMessage(ResponseConstant.SUCCESS_MSG);
			return result;
		}

			
		result.setCode(ResponseConstant.RESULT_FAILURE);
		
		return result;
	}
	
	
	//-----------------------------------------------------------------
	//								教师（把下面所有注释的学生换成教师）
	//得到所有学生
		@Autowired
		private TeacherService teacherService;
		@RequestMapping(value = "/getTeas.json", method = RequestMethod.GET)
		public @ResponseBody List<TeacherBean> getTeas() {
		
			List<TeacherBean> teacherBean = teacherService.getTeachers();
//			System.out.println(studentBean.getCollegeName());
			return teacherBean;
		}
		
		//添加教师（教师表和教师登陆表都要添加,以及添加教师的默认群消息）
		@Autowired
		private LoginTeaService loginTeaService;
		@Autowired
		private GMService gMService;
		@RequestMapping(value = "/addTea.json", method = RequestMethod.GET)
		public @ResponseBody ResponseResult addTea(
				@RequestParam(value = "teaId", required = true) String teaId,
				@RequestParam(value = "teaName", required = true) String teaName,
				@RequestParam(value = "collegeName", required = true) String collegeName,
				@RequestParam(value = "teaGrade", required = true) String teaGrade,
				@RequestParam(value = "password", required = true) String password) {
			
			
			ResponseResult result = new ResponseResult();
			if (StringUtils.isEmpty(teaId)) {
				result.setCode(ResponseConstant.PARAMS_ERROR);
				result.setMessage(ResponseConstant.PARAMS_MSG);
			}
			if ( (teacherService.addTeacher(new TeacherBean(teaId, teaName, collegeName, teaGrade)) ==1)&&
			 (loginTeaService.addLoginTea(teaId, password) ==1) &&
			 (gMService.addGM(teaId, "暂无群消息") ==1)) {
				
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
				return result;
			}
			
				
			result.setCode(ResponseConstant.RESULT_FAILURE);
			
			return result;
		}
		
		
		//修改学生（学生表和学生登陆表都要修改）
		@RequestMapping(value = "/updTea.json", method = RequestMethod.GET)
		public @ResponseBody ResponseResult updTea(
				@RequestParam(value = "teaId", required = true) String teaId,
				@RequestParam(value = "teaName", required = true) String teaName,
				@RequestParam(value = "collegeName", required = true) String collegeName,
				@RequestParam(value = "teaGrade", required = true) String teaGrade,
				@RequestParam(value = "password", required = true) String password) {
			
			
			ResponseResult result = new ResponseResult();
			if (StringUtils.isEmpty(teaId)) {
				result.setCode(ResponseConstant.PARAMS_ERROR);
				result.setMessage(ResponseConstant.PARAMS_MSG);
			}
			if ( (teacherService.updTeacher(new TeacherBean(teaId, teaName, collegeName, teaGrade)) ==1)&&
			 (loginTeaService.updLoginTea(teaId, password) ==1)) {
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
				return result;
			}
				
			result.setCode(ResponseConstant.RESULT_FAILURE);
			
			return result;
		}
		
		//删除老师
		@RequestMapping(value = "/delTea.json", method = RequestMethod.GET)
		public @ResponseBody ResponseResult delTea(
				@RequestParam(value = "teaId", required = true) String teaId) {
			
			ResponseResult result = new ResponseResult();
			if (StringUtils.isEmpty(teaId)) {
				result.setCode(ResponseConstant.PARAMS_ERROR);
				result.setMessage(ResponseConstant.PARAMS_MSG);
			}
		
			List<StudentBean> studentBeans = sTGroupService.findStudentsByTeaId(teaId);
			for(StudentBean studentBean:studentBeans){
				sTGroupService.delBind(studentBean.getStuId());
			}
			
			
			
			if ((gMService.delGM(teaId) == 1)&&
			(loginTeaService.delLoginTea(teaId) ==1)&&
			(teacherService.delTeacher(teaId) ==1)) {
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
				return result;
			}
				
			result.setCode(ResponseConstant.RESULT_FAILURE);
			
			return result;
		}
		
		//发布公告
		@Autowired
		private InformationService informationService;
		@RequestMapping(value = "/setInfo.json", method = RequestMethod.GET)
		public @ResponseBody ResponseResult setInfo(
				@RequestParam(value = "inforContent", required = true) String inforContent) {
			
			ResponseResult result = new ResponseResult();
			if (StringUtils.isEmpty(inforContent)) {
				result.setCode(ResponseConstant.PARAMS_ERROR);
				result.setMessage(ResponseConstant.PARAMS_MSG);
				return result;
			}
			
			if(informationService.setInfor(inforContent) == 1){
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
				return result;
			}
			
			result.setCode(ResponseConstant.RESULT_FAILURE);
			return result;
		}
		
		
	
		@RequestMapping(value = "/bindST.json", method = RequestMethod.GET)
		public @ResponseBody ResponseResult bindST(
				@RequestParam(value = "stuId", required = true) String stuId,
				@RequestParam(value = "teaId", required = true) String teaId) {
			
			ResponseResult result = new ResponseResult();
			if (StringUtils.isEmpty(stuId) || StringUtils.isEmpty(teaId)) {
				result.setCode(ResponseConstant.PARAMS_ERROR);
				result.setMessage(ResponseConstant.PARAMS_MSG);
				return result;
			}
			
			if(sTGroupService.addBind(stuId, teaId) == 1){
				result.setCode(ResponseConstant.RESULT_SUCCESS);
				result.setMessage(ResponseConstant.SUCCESS_MSG);
				return result;
			}
			
			result.setCode(ResponseConstant.RESULT_FAILURE);
			return result;
		}
}
