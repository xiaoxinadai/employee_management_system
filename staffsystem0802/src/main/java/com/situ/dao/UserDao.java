package com.situ.dao;

import com.situ.model.User;

/**
 * 数据库中用户的存取
 * @author adai
 */
public interface UserDao {
	//查询数据库中的用户信息
	User findByusername(String username);
	
	
}
