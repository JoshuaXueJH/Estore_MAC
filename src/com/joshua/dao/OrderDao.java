package com.joshua.dao;

import java.sql.SQLException;
import java.util.List;

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

	/**
	 * 根据用户id查找相对应订单orders
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	List<Order> findOrdersByUserID(int id) throws SQLException;

	/**
	 * 根绝order id查找对应的orderitems
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	List<OrderItem> findOrderItemByOrderID(String id) throws SQLException;

	/**
	 * 根据订单id删除订单项
	 * @param orderID
	 * @throws SQLException 
	 */
	void delOrderItemByOrderID(String orderID) throws SQLException;

	/**
	 * 根据订单id删除订单
	 * @param orderID
	 * @throws SQLException 
	 */
	void delOderByOrderID(String orderID) throws SQLException;

}
