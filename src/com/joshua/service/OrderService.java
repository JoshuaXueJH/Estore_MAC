package com.joshua.service;

import java.util.List;

import com.joshua.annotation.Tran;
import com.joshua.domain.Order;
import com.joshua.domain.OrderListForm;
import com.joshua.domain.SaleListForm;

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

	/**
	 * 根据订单id查找订单
	 * @param p2_Order
	 * @return
	 */
	Order findOrderById(String p2_Order);

	/**
	 * 修改订单的支付状态
	 * @param r6_Order
	 * @param i
	 */
	void changePayState(String r6_Order, int i);

	/**
	 * 返回销售榜单列表
	 * @return
	 */
	List<SaleListForm> saleList();

}
