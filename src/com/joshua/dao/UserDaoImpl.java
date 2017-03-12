package com.joshua.dao;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.joshua.domain.User;
import com.joshua.util.DaoUtils;
import com.joshua.util.TranManager;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserDaoImpl implements UserDao {

	@Override
	public void addUser(User user) throws SQLException {
		String sql = "insert into users values(null,?,?,?,?,?,?,?,null)";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		runner.update(sql, user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getRole(),
				user.getState(), user.getActivecode());

	}

	@Override
	public User findUserByName(String username) throws SQLException {
		String sql = "select * from users where username=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		return runner.query(sql, new BeanHandler<User>(User.class), username);
	}

	@Override
	public User findUserByActiveCode(String activeCode) throws SQLException {
		String sql = "select * from users where activecode=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		return runner.query(sql, new BeanHandler<User>(User.class), activeCode);
	}

	@Override
	public void deleteUserById(int id) throws SQLException {
		String sql = "delete from users where id = ?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		runner.update(sql, id);
	}

	@Override
	public void updateUserStatus(int id) throws SQLException {
		String sql = "update users set state=1 where id = ?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		runner.update(sql, id);
	}

	@Override
	public User findUserByNameAndPsw(String username, String password) throws SQLException {
		String sql = "select * from users where username=? and password=?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		return runner.query(sql, new BeanHandler<User>(User.class), username, password);
	}

	@Override
	public User findUserByID(int id) throws SQLException {
		String sql = "select * from users where id=?";
		QueryRunner runner = new QueryRunner(DaoUtils.getSource());
		return runner.query(sql, new BeanHandler<User>(User.class), id);
	}

	@Override
	public Object hasUserName(String username) throws SQLException {
		String sql = "select * from users where username=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		return runner.query(sql, new BeanHandler<User>(User.class), username);
	}

}
