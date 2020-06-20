package com.lrr.server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.LoginRoot;
import com.lrr.server.mapping.LoginStuMapper;
import com.lrr.server.mapping.LoginRootMapper;
import com.lrr.server.utils.Md5Util;
import com.lrr.server.utils.MyBatisService;

@Service("LoginRootService")
public class LoginRootService {

	
	/**
	 * 学生登录
	 * @param stuId
	 * @param password
	 * @return
	 */
	public LoginRoot getLoginRoot(String RootId,String password){
		SqlSession session = MyBatisService.getSession();
		LoginRootMapper mapper = session.getMapper(LoginRootMapper.class);
		LoginRoot loginRoot = mapper.findLoginRoot(RootId, password);
		session.commit();
		session.close();
		
		return loginRoot;
		
	}
}
