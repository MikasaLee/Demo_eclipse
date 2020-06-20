package com.lrr.server.mapping;

import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.LoginRoot;
import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.LoginTea;

public interface LoginRootMapper {



	/**
	 * 用户登录接口
	 * 
	 * @param stuId
	 * @param password
	 * @return
	 */
	LoginRoot findLoginRoot(@Param("rootId")String rootId,@Param("password")String password);
}
