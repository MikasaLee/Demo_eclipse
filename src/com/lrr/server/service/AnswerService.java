package com.lrr.server.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.lrr.server.entity.AnswerBean;
import com.lrr.server.entity.GroupMessageBean;
import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.QuestionBean;
import com.lrr.server.entity.StudentBean;
import com.lrr.server.mapping.AnswerMapper;
import com.lrr.server.mapping.GMMapper;
import com.lrr.server.mapping.LoginStuMapper;
import com.lrr.server.mapping.QuestionMapper;
import com.lrr.server.mapping.StudentMapper;
import com.lrr.server.utils.Md5Util;
import com.lrr.server.utils.MyBatisService;

@Service("AnswerService")
public class AnswerService {

	public AnswerBean getAnsByQuesId(int QuesId){
		SqlSession session = MyBatisService.getSession();
		AnswerMapper mapper = session.getMapper(AnswerMapper.class);
		AnswerBean answerBean = mapper.findAnsByQuesId(QuesId);
		
		session.commit();
		session.close();
		return answerBean;
	}
	
	public int updateAnsByQuesId(int QuesId,String teaContent){
		SqlSession session = MyBatisService.getSession();
		AnswerMapper mapper = session.getMapper(AnswerMapper.class);
		int i = mapper.updateAnsByQuesId(QuesId, teaContent);
		
		session.commit();
		session.close();
		return i;
	}
	
	
	public int addAnswer(int quesId,String teaContent){
		SqlSession session = MyBatisService.getSession();
		AnswerMapper mapper = session.getMapper(AnswerMapper.class);
		int i =mapper.addAnswer(new AnswerBean(quesId, teaContent));
		
		session.commit();
		session.close();
		return i;
	}
	
	public int delAnswer(int quesId){
		SqlSession session = MyBatisService.getSession();
		AnswerMapper mapper = session.getMapper(AnswerMapper.class);
		int i =mapper.delAnsByQuesId(quesId);
		
		session.commit();
		session.close();
		return i;
	}
}
