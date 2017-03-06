package com.joshua.service;

import java.util.List;

import com.joshua.annotation.Tran;
import com.joshua.domain.Order;
import com.joshua.domain.OrderListForm;

public interface OrderService extends Service{

	/**
	 * 生成订单
	 * @param order
	 */
	@Tran
	void addOrder(Order order);

	/**
	 * 根据userid查找orderList
	 * @param id
	 * @return
	 */
	List<OrderListForm> findOrderListByUserID(int id);

	/**
	 * 根据订单id删除相应的订单
	 * @param orderID
	 */
	@Tran
	void deleteOrderByID(String orderID);

}
