package com.lrr.server.mapping;

import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.InformationBean;
import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.StudentBean;

public interface InformationMapper {

	/**
	 * 用户接口
	 * 
	 * @param stuId
	 * @param password
	 * @return
	 */
	InformationBean getInfo();
	
	
	int  setInfo(@Param("inforContent")String inforContent);
}
