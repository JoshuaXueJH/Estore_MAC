package com.joshua.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.joshua.domain.Order;
import com.joshua.domain.OrderItem;
import com.joshua.domain.Prod;
import com.joshua.domain.User;
import com.joshua.factory.BasicFactory;
import com.joshua.service.OrderService;

/**
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService orderService = BasicFactory.getFactory().getService(OrderService.class);

		try {
			// 从cartMap获取订单信息
			Map<Prod, Integer> cartMap = (Map<Prod, Integer>) request.getSession().getAttribute("cartMap");
			Order order = new Order();
			double money = 0;
			List<OrderItem> list = new ArrayList<OrderItem>();
			BeanUtils.populate(order, request.getParameterMap());
			order.setId(UUID.randomUUID().toString());
			order.setPaystate(0);
			order.setUser_id(((User) request.getSession().getAttribute("user")).getId());
			for (Map.Entry<Prod, Integer> entry : cartMap.entrySet()) {
				// 计算订单价格，向orderitem表中添加信息
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder_id(order.getId());
				orderItem.setProduct_id(entry.getKey().getId());
				orderItem.setBuynum(entry.getValue());
				list.add(orderItem);
				money += entry.getKey().getPrice() * entry.getValue();
			}
			order.setMoney(money);
			order.setOrderItem(list);
			// 向order表中添加订单
			orderService.addOrder(order);
			// 清空cartMap
			cartMap.clear();
			// 返回主页
			response.getWriter().write("订单生成成功， 请前往支付");
			response.setHeader("refresh", "3;url=index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
