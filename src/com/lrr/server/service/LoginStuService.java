package com.lrr.server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lrr.server.entity.LoginStu;
import com.lrr.server.mapping.LoginStuMapper;
import com.lrr.server.utils.Md5Util;
import com.lrr.server.utils.MyBatisService;

@Service("LoginStuService")
public class LoginStuService {

	
	/**
	 * 学生登录
	 * @param stuId
	 * @param password
	 * @return
	 */
	public LoginStu getLoginStu(String stuId,String password){
		SqlSession session = MyBatisService.getSession();
		LoginStuMapper mapper = session.getMapper(LoginStuMapper.class);
		LoginStu loginStu = mapper.findLoginStu(stuId, password);
		
		session.commit();
		session.close();
		return loginStu;
	}
	
	public int addLoginStu(String stuId,String password){
		SqlSession session = MyBatisService.getSession();
		LoginStuMapper mapper = session.getMapper(LoginStuMapper.class);
		int loginStu = mapper.addLoginStu(stuId, password);
		session.commit();
		session.close();
		return loginStu;
	}
	
	public int updLoginStu(String stuId,String password){
		SqlSession session = MyBatisService.getSession();
		LoginStuMapper mapper = session.getMapper(LoginStuMapper.class);
		int loginStu = mapper.updLoginStu(stuId, password);
		
		session.commit();
		session.close();
		return loginStu;
	}
	
	public int delLoginStu(String stuId){
		SqlSession session = MyBatisService.getSession();
		LoginStuMapper mapper = session.getMapper(LoginStuMapper.class);
		int loginStu = mapper.delLoginStu(stuId);
		
		session.commit();
		session.close();
		return loginStu;
	}
}
