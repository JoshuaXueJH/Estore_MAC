package com.joshua.dao;

import java.sql.SQLException;

import com.joshua.domain.OrderListForm;
import com.joshua.domain.User;

public interface UserDao extends Dao{

	/**
	 * 向数据库中添加时用户
	 * 
	 * @param user
	 * @throws SQLException 
	 */
	void addUser(User user) throws SQLException;

	/**
	 * 数据库中根据用户名查找记录
	 * 
	 * @param username
	 * @return
	 * @throws SQLException 
	 */
	User findUserByName(String username) throws SQLException;

	/**
	 * 根据激活码查找用户
	 * @param activeCode
	 * @return
	 * @throws SQLException 
	 */
	User findUserByActiveCode(String activeCode) throws SQLException;

	/**
	 * 根据用户id删除用户
	 * @param id
	 * @throws SQLException 
	 */
	void deleteUserById(int id) throws SQLException;

	/**
	 * 将用户的state状态更改为1
	 * @param id
	 * @throws SQLException 
	 */
	void updateUserStatus(int id) throws SQLException;

	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	User findUserByNameAndPsw(String username, String password) throws SQLException;

	/**
	 * 根据用户id查找用户
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	User findUserByID(int id) throws SQLException;

}
