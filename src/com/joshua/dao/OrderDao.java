package com.joshua.dao;

import java.sql.SQLException;

import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;

public interface OrderDao extends Dao {

	/**
	 * 向数据库order表中增加一条订单记录
	 * 
	 * @param order
	 * @throws SQLException 
	 */
	void addOrder(Order order) throws SQLException;

	/**
	 * 向数据库orderitem表中增加订单项记录
	 * 
	 * @param item
	 * @throws SQLException 
	 */
	void addOrderItem(OrderItem item) throws SQLException;

}
