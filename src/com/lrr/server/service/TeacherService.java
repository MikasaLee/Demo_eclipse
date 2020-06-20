package com.lrr.server.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.TeacherBean;
import com.lrr.server.entity.TeacherBean;
import com.lrr.server.mapping.LoginStuMapper;
import com.lrr.server.mapping.TeacherMapper;
import com.lrr.server.mapping.TeacherMapper;
import com.lrr.server.utils.Md5Util;
import com.lrr.server.utils.MyBatisService;

@Service("TeacherService")
public class TeacherService {

	
	/**
	 * 学生登录
	 * @param stuId
	 * @param password
	 * @return
	 */
	public TeacherBean getTeaById(String teaId){
		SqlSession session = MyBatisService.getSession();
		TeacherMapper mapper = session.getMapper(TeacherMapper.class);
		TeacherBean teacherBean = mapper.findTeaById(teaId);
		
		session.commit();
		session.close();
		return teacherBean;
	}
	
	public TeacherBean getTeaByStuId(String stuId){
		SqlSession session = MyBatisService.getSession();
		TeacherMapper mapper = session.getMapper(TeacherMapper.class);
		TeacherBean teacherBean = mapper.findTeaByStuId(stuId);
		
		session.commit();
		session.close();
		return teacherBean;
	}
	
	
	public List<TeacherBean> getTeachers(){
		SqlSession session = MyBatisService.getSession();
		TeacherMapper mapper = session.getMapper(TeacherMapper.class);
		List<TeacherBean> TeacherBeans = mapper.getTeas();
		session.commit();
		session.close();
		return TeacherBeans;
	}
	
	public int addTeacher(TeacherBean TeacherBean){
		SqlSession session = MyBatisService.getSession();
		TeacherMapper mapper = session.getMapper(TeacherMapper.class);
		int i = mapper.addTeacher(TeacherBean);
		session.commit();
		session.close();
		return i;
	}
	
	public int updTeacher(TeacherBean TeacherBean){
		SqlSession session = MyBatisService.getSession();
		TeacherMapper mapper = session.getMapper(TeacherMapper.class);
		int i = mapper.updTeacher(TeacherBean);
		session.commit();
		session.close();
		return i;
	}
	public int delTeacher(String teaId){
		SqlSession session = MyBatisService.getSession();
		TeacherMapper mapper = session.getMapper(TeacherMapper.class);
		int i = mapper.delTeacher(teaId);
		session.commit();
		session.close();
		return i;
	}
}
