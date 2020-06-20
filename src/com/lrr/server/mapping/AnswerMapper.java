package com.lrr.server.mapping;

import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.AnswerBean;
import com.lrr.server.entity.QuestionBean;


public interface AnswerMapper {

	int addAnswer(AnswerBean ans);
	
	
	AnswerBean findAnsByQuesId(@Param("quesId")int quesId);
	
	int updateAnsByQuesId(@Param("quesId")int quesId,@Param("teaContent")String teaContent);
	
	int delAnsByQuesId(@Param("quesId")int quesId);
}
