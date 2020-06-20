package com.lrr.server.mapping;

import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.RootBean;
import com.lrr.server.entity.StudentBean;

public interface RootMapper {



	/**
	 * 用户接口
	 * 
	 * @param stuId
	 * @param password
	 * @return
	 */
	RootBean findRootById(@Param("rootId")String rootId);
}
