package com.lrr.server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lrr.server.entity.LoginTea;
import com.lrr.server.mapping.LoginTeaMapper;
import com.lrr.server.utils.MyBatisService;

@Service("LoginTeaService")
public class LoginTeaService {

	
	/**
	 * 学生登录
	 * @param stuId
	 * @param password
	 * @return
	 */
	public LoginTea getLoginTea(String teaId,String password){
		SqlSession session = MyBatisService.getSession();
		LoginTeaMapper mapper = session.getMapper(LoginTeaMapper.class);
		LoginTea loginTea = mapper.findLoginTea(teaId, password);
		session.commit();
		session.close();
		
		return loginTea;
		
	}
	
	public int addLoginTea(String teaId,String password){
		SqlSession session = MyBatisService.getSession();
		LoginTeaMapper mapper = session.getMapper(LoginTeaMapper.class);
		int LoginTea = mapper.addLoginTea(teaId, password);
		session.commit();
		session.close();
		return LoginTea;
	}
	
	public int updLoginTea(String teaId,String password){
		SqlSession session = MyBatisService.getSession();
		LoginTeaMapper mapper = session.getMapper(LoginTeaMapper.class);
		int LoginTea = mapper.updLoginTea(teaId, password);
		
		session.commit();
		session.close();
		return LoginTea;
	}
	
	public int delLoginTea(String teaId){
		SqlSession session = MyBatisService.getSession();
		LoginTeaMapper mapper = session.getMapper(LoginTeaMapper.class);
		int LoginTea = mapper.delLoginTea(teaId);
		
		session.commit();
		session.close();
		return LoginTea;
	}
}
