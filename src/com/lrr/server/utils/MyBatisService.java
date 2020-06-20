package com.lrr.server.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisService {

	public static SqlSession getSession() {
		InputStream inputStream = MyBatisService.class.getClassLoader()
				.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		return sessionFactory.openSession();
	}

}
