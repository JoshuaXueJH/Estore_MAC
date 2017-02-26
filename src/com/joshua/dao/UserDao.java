package com.joshua.dao;

import com.joshua.domain.User;

public interface UserDao extends Dao{

	/**
	 * 向数据库中添加时用户
	 * 
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 数据库中根据用户名查找记录
	 * 
	 * @param username
	 * @return
	 */
	User findUserByName(String username);

	/**
	 * 根据激活码查找用户
	 * @param activeCode
	 * @return
	 */
	User findUserByActiveCode(String activeCode);

	/**
	 * 根据用户id删除用户
	 * @param id
	 */
	void deleteUserById(int id);

	/**
	 * 将用户的state状态更改为1
	 * @param id
	 */
	void updateUserStatus(int id);

	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	User findUserByNameAndPsw(String username, String password);

}
