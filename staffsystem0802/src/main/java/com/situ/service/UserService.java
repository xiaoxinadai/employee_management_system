package com.situ.service;

import com.situ.model.User;

/**
 * 用户业务接口
 * @author adai
 */
public interface UserService {
	//通过用户名查询唯一用户
	User findByUsername(String username);
	
	/*
	 * 对用户输入的用户名进行验证
	 * 正确则返回true
	 */
	boolean check(User loginUser);
	
	boolean check(User loginUser,User dbUser);
}
