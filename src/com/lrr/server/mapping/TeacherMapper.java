package com.lrr.server.mapping;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.TeacherBean;

public interface TeacherMapper {



	/**
	 * 用户接口
	 * 
	 * @param stuId
	 * @param password
	 * @return
	 */
	TeacherBean findTeaById(@Param("teaId")String teaId);
	
	TeacherBean findTeaByStuId(@Param("stuId")String stuId);
	
	ArrayList<TeacherBean> getTeas();
	
	int addTeacher(TeacherBean teacherBean);
	
	int updTeacher(TeacherBean teacherBean);
	
	int delTeacher(@Param("teaId")String teaId);
}
