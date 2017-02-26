package com.joshua.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joshua.domain.Prod;
import com.joshua.factory.BasicFactory;
import com.joshua.service.ProdService;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService prodService = BasicFactory.getFactory().getService (ProdService.class);

		// 根据id查找要添加到购物车的商品
		Prod prod = prodService.findProdByID(request.getParameter("id"));
		// 将查找到的商品添加到购物车
		Map<Prod, Integer> cartMap = (Map<Prod, Integer>) request.getSession().getAttribute("cartMap");
		if (prod == null) {
			throw new RuntimeException("无法查找到此商品，添加到购物车失败");
		} else {
			cartMap.put(prod, cartMap.containsKey(prod) ? cartMap.get(prod) + 1 : 1);
		}
		// 页面重定向
		response.sendRedirect("cart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
