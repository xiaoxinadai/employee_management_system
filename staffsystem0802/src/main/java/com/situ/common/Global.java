package com.situ.common;

import java.sql.Connection;

import com.situ.utils.JdbcUtils;

/**
 * 公用类
 * 连接数据库
 * 
 * @author adai
 */

public class Global {
	
	public static final String LOGIN_USER_KEY = "#present_login_user_key";
	
	/*
	 * 应用启动时获取数据库的url、root、password
	 */
	public static String JDBC_URL;
	public static String JDBC_USER;
	public static String JDBC_PASSWORD;
	
	public static Connection getConnection() {
		return JdbcUtils.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
	
	
	
}
