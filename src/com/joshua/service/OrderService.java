package com.joshua.service;

import com.joshua.annotation.Tran;
import com.joshua.domain.Order;

public interface OrderService extends Service{

	/**
	 * 生成订单
	 * @param order
	 */
	@Tran
	void addOrder(Order order);

}
