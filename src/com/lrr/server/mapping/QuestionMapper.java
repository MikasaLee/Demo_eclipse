package com.lrr.server.mapping;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.QuestionBean;

public interface QuestionMapper {

	
	int addQuestion(QuestionBean ques);
	
	
	ArrayList<QuestionBean> findQuesByStuId(@Param("stuId")String stuId);
	QuestionBean findQuestion(@Param("stuId")String stuId,@Param("stuContent")String stuContent);
	
	int delQuestion(@Param("stuId")String stuId);
}
