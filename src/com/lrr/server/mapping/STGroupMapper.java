package com.lrr.server.mapping;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.StudentBean;
import com.lrr.server.entity.TeacherBean;

public interface STGroupMapper {


	ArrayList<StudentBean> findStudentsByTeaId(@Param("teaId")String teaId);
	
	int addBind(@Param("stuId")String stuId, @Param("teaId")String teaId);
	
	int delBind(@Param("stuId")String stuId);
}
