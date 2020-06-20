package com.lrr.server.mapping;

import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.StudentBean;

public interface LoginStuMapper {



	/**
	 * 用户登录接口
	 * 
	 * @param stuId
	 * @param password
	 * @return
	 */
	LoginStu findLoginStu(@Param("stuId")String stuId,@Param("password")String password);
	
	int addLoginStu(@Param("stuId")String stuId,@Param("password")String password);
	int updLoginStu(@Param("stuId")String stuId,@Param("password")String password);
	int delLoginStu(@Param("stuId")String stuId);
}
