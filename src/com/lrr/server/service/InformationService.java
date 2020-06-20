package com.lrr.server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lrr.server.entity.InformationBean;
import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.StudentBean;
import com.lrr.server.mapping.InformationMapper;
import com.lrr.server.mapping.LoginStuMapper;
import com.lrr.server.mapping.StudentMapper;
import com.lrr.server.utils.Md5Util;
import com.lrr.server.utils.MyBatisService;

@Service("InformationService")
public class InformationService {


	public InformationBean getInfor(){
		SqlSession session = MyBatisService.getSession();
		InformationMapper mapper = session.getMapper(InformationMapper.class);
		InformationBean informationBean = mapper.getInfo();
		
		session.commit();
		session.close();
		return informationBean;
	}
	
	public int setInfor(String inforContent){
		SqlSession session = MyBatisService.getSession();
		InformationMapper mapper = session.getMapper(InformationMapper.class);
		int i = mapper.setInfo(inforContent);
		
		session.commit();
		session.close();
		return i;
	}
}
