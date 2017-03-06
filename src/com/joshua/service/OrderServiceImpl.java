package com.joshua.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.joshua.dao.OrderDao;
import com.joshua.dao.ProdDao;
import com.joshua.dao.UserDao;
import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;
import com.joshua.domain.OrderListForm;
import com.joshua.domain.Prod;
import com.joshua.factory.BasicFactory;

public class OrderServiceImpl implements OrderService {
	UserDao userDao = BasicFactory.getFactory().getDao(UserDao.class);
	OrderDao orderDao = BasicFactory.getFactory().getDao(OrderDao.class);
	ProdDao prodDao = BasicFactory.getFactory().getDao(ProdDao.class);

	@Override
	public void addOrder(Order order) {
		try {
			orderDao.addOrder(order);
			for (OrderItem item : order.getOrderItem()) {
				orderDao.addOrderItem(item);
				prodDao.delPnum(item.getProduct_id(), item.getBuynum());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderListForm> findOrderListByUserID(int id) {
		try {
			List<OrderListForm> list = new ArrayList<OrderListForm>();
			List<Order> orderList = orderDao.findOrdersByUserID(id);
			for (Order order : orderList) {
				OrderListForm olf = new OrderListForm();
				BeanUtils.copyProperties(olf, order);
				olf.setUsername(userDao.findUserByID(id).getUsername());
				Map<Prod, Integer> map = new HashMap<Prod, Integer>();
				List<OrderItem> itemList = orderDao.findOrderItemByOrderID(order.getId());
				for (OrderItem orderItem : itemList) {
					map.put(prodDao.findProdByID(orderItem.getProduct_id()), orderItem.getBuynum());
				}
				olf.setProdMap(map);
				list.add(olf);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteOrderByID(String orderID) {
		try {
			List<OrderItem> orderItems = orderDao.findOrderItemByOrderID(orderID);
			for (OrderItem item : orderItems) {
				prodDao.addPnum(item.getProduct_id(), item.getBuynum());
			}
			orderDao.delOrderItemByOrderID(orderID);
			orderDao.delOderByOrderID(orderID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
