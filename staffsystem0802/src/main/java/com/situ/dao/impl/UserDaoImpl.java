package com.situ.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.situ.common.Global;
import com.situ.dao.UserDao;
import com.situ.model.User;
import com.situ.utils.JdbcUtils;
import com.situ.utils.JdbcUtils.BeanListHandler;
import com.situ.utils.JdbcUtils.ResultSetHandler;

/**
 * 实现从数据库中查找登录用户信息
 * @author adai
 *
 */
public class UserDaoImpl implements UserDao{
	/*
	 * 结果集转换器
	 */
	private final ResultSetHandler<List<User>> rsh = new BeanListHandler<User>(User.class);
	
	@Override
	public User findByusername(String username) {
		//建立数据库连接
		Connection conn = Global.getConnection();
		
		try {
			String sql = "Select username,password from t_user where username = ?";
			
			List<User> users = JdbcUtils.query(conn, rsh, sql, username);
			if(users.size()>=1) { //查去到用户信息，则返回用户信息
				return users.get(0);
				
			}else {  //没有查到则返回空
				return null;
			}
		} finally { //关闭数据库连接
			JdbcUtils.closeConnection(conn);
		}
	}
	
	
	
	
	
}
