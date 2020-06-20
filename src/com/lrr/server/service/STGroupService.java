package com.lrr.server.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.QuestionBean;
import com.lrr.server.entity.StudentBean;
import com.lrr.server.mapping.LoginStuMapper;
import com.lrr.server.mapping.QuestionMapper;
import com.lrr.server.mapping.STGroupMapper;
import com.lrr.server.mapping.StudentMapper;
import com.lrr.server.utils.Md5Util;
import com.lrr.server.utils.MyBatisService;

@Service("STGroupService")
public class STGroupService {

	
	public List<StudentBean> findStudentsByTeaId(String teaId){
		SqlSession session = MyBatisService.getSession();
		STGroupMapper mapper = session.getMapper(STGroupMapper.class);
		
		List<StudentBean> studentBeans = mapper.findStudentsByTeaId(teaId);
		session.commit();
		session.close();
		return studentBeans;
	}
	
	public int addBind(String stuId,String teaId){
		SqlSession session = MyBatisService.getSession();
		STGroupMapper mapper = session.getMapper(STGroupMapper.class);
		
		int i = mapper.addBind(stuId, teaId);
		
		session.commit();
		session.close();
		return i;
	}
	
	public int delBind(String stuId){
		SqlSession session = MyBatisService.getSession();
		STGroupMapper mapper = session.getMapper(STGroupMapper.class);
		
		int i = mapper.delBind(stuId);
		
		session.commit();
		session.close();
		return i;
	}
}
