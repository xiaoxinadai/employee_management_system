package com.situ.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 启动服务器之后读取文件信息
 * 将文件中的内容给到jdbc
 * @author adai
 *
 */

@WebListener
public class ProgramStartListenter implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//IO流读取文件中的内容给到jdbc
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
		
		//读写Properties文件，Properties文件专门用来存储一些键值对
		Properties prop = new Properties();
		
		try {
			prop.load(is);
			Global.JDBC_URL = prop.getProperty("jdbc.url");
			Global.JDBC_USER = prop.getProperty("jdbc.user");
			Global.JDBC_PASSWORD = prop.getProperty("jdbc.password");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
