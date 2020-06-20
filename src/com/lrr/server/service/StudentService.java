package com.lrr.server.service;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.QuestionBean;
import com.lrr.server.entity.StudentBean;
import com.lrr.server.mapping.LoginStuMapper;
import com.lrr.server.mapping.QuestionMapper;
import com.lrr.server.mapping.StudentMapper;
import com.lrr.server.utils.Md5Util;
import com.lrr.server.utils.MyBatisService;

@Service("StudentService")
public class StudentService {

	public StudentBean getStudent(String stuId){
		SqlSession session = MyBatisService.getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		StudentBean studentBean = mapper.findStuById(stuId);

		session.commit();
		session.close();
		return studentBean;
	}
	public List<StudentBean> getStudents(){
		SqlSession session = MyBatisService.getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		List<StudentBean> studentBeans = mapper.getStus();
		session.commit();
		session.close();
		return studentBeans;
	}
	
	public int addStudent(StudentBean studentBean){
		SqlSession session = MyBatisService.getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		int i = mapper.addStudent(studentBean);
		session.commit();
		session.close();
		return i;
	}
	
	public int updStudent(StudentBean studentBean){
		SqlSession session = MyBatisService.getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		int i = mapper.updStudent(studentBean);
		session.commit();
		session.close();
		return i;
	}
	public int delStudent(String stuId){
		SqlSession session = MyBatisService.getSession();
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		int i = mapper.delStudent(stuId);
		session.commit();
		session.close();
		return i;
	}
}
