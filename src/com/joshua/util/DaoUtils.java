package com.joshua.util;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Connection;

public class DaoUtils {
	private static DataSource source = new ComboPooledDataSource();

	private DaoUtils() {

	}

	public static DataSource getSource() {
		return source;
	}

	public static Connection getConnection() {
		try {
			return (Connection) source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
