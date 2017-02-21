package com.joshua.dao;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.joshua.domain.User;
import com.joshua.util.DaoUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserDaoImpl implements UserDao {

	@Override
	public void addUser(User user) {
		String sql = "insert into users values(null,?,?,?,?,?,?,?,null)";

		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(),
					user.getRole(), user.getState(), user.getActivecode());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public User findUserByName(String username) {
		String sql = "select * from users where username=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class), username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserByActiveCode(String activeCode) {
		String sql = "select * from users where activecode=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class), activeCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteUserById(int id) {
		String sql = "delete from users where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updateUserStatus(int id) {
		String sql = "update users set state=1 where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserByNameAndPsw(String username, String password) {
		String sql = "select * from users where username=? and password=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class), username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
