package com.lrr.server.mapping;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.StudentBean;

public interface StudentMapper {



	/**
	 * 用户接口
	 * 
	 * @param stuId
	 * @param password
	 * @return
	 */
	StudentBean findStuById(@Param("stuId")String stuId);
	
	ArrayList<StudentBean> getStus();
	
	int addStudent(StudentBean studentBean);
	
	int updStudent(StudentBean studentBean);
	
	int delStudent(@Param("stuId")String stuId);
}
