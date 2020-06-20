package com.lrr.server.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lrr.server.entity.GroupMessageBean;
import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.QuestionBean;
import com.lrr.server.entity.StudentBean;
import com.lrr.server.mapping.GMMapper;
import com.lrr.server.mapping.LoginStuMapper;
import com.lrr.server.mapping.QuestionMapper;
import com.lrr.server.mapping.StudentMapper;
import com.lrr.server.utils.Md5Util;
import com.lrr.server.utils.MyBatisService;

@Service("QuestionService")
public class QuestionService {

	public List<QuestionBean> getAllQues(String stuId){
		SqlSession session = MyBatisService.getSession();
		QuestionMapper mapper = session.getMapper(QuestionMapper.class);
		List<QuestionBean> questionBeans = mapper.findQuesByStuId(stuId);
		
		session.commit();
		session.close();
		return questionBeans;
	}
	
	public QuestionBean getQuestion(String stuId,String stuContent){
		SqlSession session = MyBatisService.getSession();
		QuestionMapper mapper = session.getMapper(QuestionMapper.class);
		QuestionBean questionBean = mapper.findQuestion(stuId, stuContent);
		
		
		session.commit();
		session.close();
		return questionBean;
	}
	public int addQuestion(String stuId,String StuContent){
		SqlSession session = MyBatisService.getSession();
		QuestionMapper mapper = session.getMapper(QuestionMapper.class);
		
		int i =mapper.addQuestion(new QuestionBean(stuId,StuContent));
		session.commit();
		session.close();
		return i;
	}
	public int delQuestion(String stuId){
		SqlSession session = MyBatisService.getSession();
		QuestionMapper mapper = session.getMapper(QuestionMapper.class);
		
		int i =mapper.delQuestion(stuId);
		session.commit();
		session.close();
		return i;
	}
	
}
