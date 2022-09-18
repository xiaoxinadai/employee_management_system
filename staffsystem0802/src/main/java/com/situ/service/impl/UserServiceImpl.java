package com.situ.service.impl;

import com.situ.dao.UserDao;
import com.situ.dao.impl.UserDaoImpl;
import com.situ.model.User;
import com.situ.service.UserService;
import com.situ.utils.Md5Utils;

/**
 * 用户登录检查业务实现
 * 
 * @author adai
 *
 */
public class UserServiceImpl implements UserService {

	private final UserDao dao = new UserDaoImpl();

	@Override
	public boolean check(User loginUser) {
		/*
		 * 通过dao查询到数据库中用户的信息 判断信息是否存在
		 */
		User dbUser = dao.findByusername(loginUser.getUsername());

		if (dbUser == null) {
			return false;

		}

		// 如果不为空则判断两者是否匹配
		return check(loginUser, dbUser);
	}

	@Override
	public User findByUsername(String username) {
		return dao.findByusername(username);
	}

	@Override
	public boolean check(User loginUser, User targetUser) {
		
		String encrypt = Md5Utils.encrypt(loginUser.getPassword() + "{" + loginUser.getUsername() + "}");
		// 与数据库中的秘密啊进行匹配
		return encrypt.equals(targetUser.getPassword());
	}

}
