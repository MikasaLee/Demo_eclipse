package com.lrr.server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lrr.server.entity.GroupMessageBean;
import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.StudentBean;
import com.lrr.server.mapping.GMMapper;
import com.lrr.server.mapping.LoginStuMapper;
import com.lrr.server.mapping.StudentMapper;
import com.lrr.server.utils.Md5Util;
import com.lrr.server.utils.MyBatisService;
@Service("GMService")
public class GMService {

	public GroupMessageBean getGM(String teaId){
		SqlSession session = MyBatisService.getSession();
		GMMapper mapper = session.getMapper(GMMapper.class);
		GroupMessageBean groupMessageBean = mapper.getGM(teaId);
		
		session.commit();
		session.close();
		return groupMessageBean;
	}
	
	public int addGM(String teaId,String gMContent){
		SqlSession session = MyBatisService.getSession();
		GMMapper mapper = session.getMapper(GMMapper.class);
		int i = mapper.insertGM(teaId, gMContent);
		
		session.commit();
		session.close();
		return i;
	}
	
	
	public int updateGM(String teaId,String gMContent){
		SqlSession session = MyBatisService.getSession();
		GMMapper mapper = session.getMapper(GMMapper.class);
		int i = mapper.updateGM(teaId, gMContent);
		
		session.commit();
		session.close();
		return i;
	}
	
	public int delGM(String teaId){
		SqlSession session = MyBatisService.getSession();
		GMMapper mapper = session.getMapper(GMMapper.class);
		int i = mapper.deleteGM(teaId);
		
		session.commit();
		session.close();
		return i;
	}
}
