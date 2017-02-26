package com.joshua.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;
import com.joshua.util.DaoUtils;
import com.joshua.util.TranManager;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void addOrder(Order order) throws SQLException {
			String sql = "insert into orders values(?,?,?,?,null,?)";
			QueryRunner runner = new QueryRunner(TranManager.getSource());
			runner.update(sql, order.getId(), order.getMoney(), order.getReceiverinfo(), order.getPaystate(),
					order.getUser_id());
	}

	@Override
	public void addOrderItem(OrderItem item) throws SQLException {
			String sql = "insert into orderitem values(?,?,?)";
			QueryRunner runner = new QueryRunner(TranManager.getSource());
			runner.update(sql, item.getOrder_id(), item.getProd_id(), item.getBuynum());
	}

}
