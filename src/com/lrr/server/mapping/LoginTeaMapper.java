package com.lrr.server.mapping;

import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.LoginTea;

public interface LoginTeaMapper {



	/**
	 * 用户登录接口
	 * 
	 * @param TeaId
	 * @param password
	 * @return
	 */
	LoginTea findLoginTea(@Param("teaId")String teaId,@Param("password")String password);
	
	int addLoginTea(@Param("teaId")String teaId,@Param("password")String password);
	int updLoginTea(@Param("teaId")String teaId,@Param("password")String password);
	int delLoginTea(@Param("teaId")String teaId);
}
