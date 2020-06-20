package com.lrr.server.mapping;

import org.apache.ibatis.annotations.Param;

import com.lrr.server.entity.GroupMessageBean;
import com.lrr.server.entity.InformationBean;
import com.lrr.server.entity.LoginStu;
import com.lrr.server.entity.StudentBean;

public interface GMMapper {

	
	GroupMessageBean getGM(@Param("teaId") String teaId);
	
	int updateGM(@Param("teaId") String teaId,@Param("gMContent") String gMContent);
	
	int insertGM(@Param("teaId") String teaId,@Param("gMContent") String gMContent);
	
	int deleteGM(@Param("teaId") String teaId);
}
