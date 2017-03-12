package com.joshua.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;
import com.joshua.domain.Prod;
import com.joshua.domain.SaleListForm;
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
		runner.update(sql, item.getOrder_id(), item.getProduct_id(), item.getBuynum());
	}

	@Override
	public List<Order> findOrdersByUserID(int id) throws SQLException {
		String sql = "select * from orders where user_id=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		return runner.query(sql, new BeanListHandler<Order>(Order.class), id);
	}

	@Override
	public List<OrderItem> findOrderItemByOrderID(String id) throws SQLException {
		String sql = "select * from orderitem where order_id=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		return runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), id);
	}

	@Override
	public void delOrderItemByOrderID(String orderID) throws SQLException {
		String sql = "delete from orderitem where order_id=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		runner.update(sql, orderID);
	}

	@Override
	public void delOderByOrderID(String orderID) throws SQLException {
		String sql = "delete from orders where id=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		runner.update(sql, orderID);
	}

	@Override
	public Order findOrderByID(String p2_Order) throws SQLException {
		String sql = "select * from orders where id=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		return runner.query(sql, new BeanHandler<Order>(Order.class), p2_Order);
	}

	@Override
	public void changePayState(String r6_Order, int i) throws SQLException {
		String sql = "update orders set paystate =? where id=?";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		runner.update(sql, i, r6_Order);
	}

	@Override
	public List<SaleListForm> saleList() throws SQLException {
		String sql = "select products.id prod_id, products.name prod_name, sum(buynum) sal_num from products, orderitem where products.id=orderitem.product_id group by products.id order by sal_num desc";
		QueryRunner runner = new QueryRunner(TranManager.getSource());
		return runner.query(sql, new BeanListHandler<SaleListForm>(SaleListForm.class));
	}

}
