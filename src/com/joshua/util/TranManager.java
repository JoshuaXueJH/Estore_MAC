package com.joshua.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TranManager {
	private TranManager() {

	}

	private static DataSource source = new ComboPooledDataSource();
	private static ThreadLocal<Boolean> isTran_local = new ThreadLocal<Boolean>() {
		protected Boolean initialValue() {
			return false;// 默认不开启事务
		};
	};
	private static ThreadLocal<Connection> proxyConn_local = new ThreadLocal<Connection>();// proxy改造过后的真是连接，其中改造了close方法
	private static ThreadLocal<Connection> realconn_local = new ThreadLocal<>();// 真是连接

	/**
	 * 开启事务
	 * 
	 * @throws SQLException
	 */
	public static void startTran() throws SQLException {
		isTran_local.set(true);// 设置事务标记为true
		Connection conn = source.getConnection();// 创建连接，当前所有线程中的数据库操作都使用这个conn
		conn.setAutoCommit(false);// 开启事务
		realconn_local.set(conn);// 为了事务完成后关闭连接，故将连接保存到当前线程中

		Connection proxyConn = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(),
				conn.getClass().getInterfaces(), new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if ("close".equals(method.getName())) {
							return null;
						} else {
							return method.invoke(conn, args);
						}
					}
				});
		proxyConn_local.set(proxyConn);
	}

	public static DataSource getSource() {
		if (isTran_local.get()) {
			return (DataSource) Proxy.newProxyInstance(source.getClass().getClassLoader(),
					source.getClass().getInterfaces(), new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							if ("getConnection".equals(method.getName())) {
								return proxyConn_local.get();
							} else {
								return method.invoke(source, args);
							}
						}
					});
		} else {
			return source;
		}
	}

	/**
	 * 提交事务
	 */
	public static void commit() {
		DbUtils.commitAndCloseQuietly(proxyConn_local.get());
	}

	/**
	 * 事务回滚
	 */
	public static void rollback() {
		DbUtils.rollbackAndCloseQuietly(proxyConn_local.get());
	}

	/**
	 * 释放资源
	 */
	public static void release() {
		DbUtils.closeQuietly(realconn_local.get());
		realconn_local.remove();
		proxyConn_local.remove();
		isTran_local.remove();
	}
}
