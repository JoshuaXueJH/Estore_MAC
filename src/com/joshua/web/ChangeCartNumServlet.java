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
 * Servlet implementation class ChangeCartNumServlet
 */
@WebServlet("/ChangeCartNumServlet")
public class ChangeCartNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeCartNumServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService prodService = BasicFactory.getFactory().getService(ProdService.class);

		// 获取cartMap，根据id查找prod
		Map<Prod, Integer> cartMap = (Map<Prod, Integer>) request.getSession().getAttribute("cartMap");
		Prod prod = prodService.findProdByID(request.getParameter("id"));
		// 将购买商品的新数量添加到cartMap
		if (prod == null) {
			throw new RuntimeException("未找到该商品！");
		} else {
			cartMap.put(prod, Integer.parseInt(request.getParameter("buyNum")));
		}
		// 重定向到cart.jsp
		response.sendRedirect("cart.jsp");
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
