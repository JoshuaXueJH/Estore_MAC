package com.joshua.service;

import com.joshua.dao.OrderDao;
import com.joshua.dao.ProdDao;
import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;
import com.joshua.factory.BasicFactory;

public class OrderServiceImpl implements OrderService {
	OrderDao orderDao = BasicFactory.getFactory().getDao(OrderDao.class);
	ProdDao prodDao = BasicFactory.getFactory().getDao(ProdDao.class);

	@Override
	public void addOrder(Order order) {
		try {
			orderDao.addOrder(order);
			for (OrderItem item : order.getOrderItem()) {
				orderDao.addOrderItem(item);
				prodDao.delPnum(item.getProd_id(), item.getBuynum());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
