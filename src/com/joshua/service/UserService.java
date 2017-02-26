package com.joshua.service;

import com.joshua.domain.User;

public interface UserService extends Service {

	/**
	 * 注册用户
	 * 
	 * @param user
	 */
	void regist(User user);

	/**
	 * 激活用户
	 * 
	 * @param activeCode
	 * @return
	 */
	User activate(String activeCode);

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

}
