package com.joshua.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.joshua.domain.User;
import com.joshua.factory.BasicFactory;
import com.joshua.service.UserService;
import com.joshua.util.MD5Utils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = BasicFactory.getFactory().getInstance(UserService.class);
		// 验证用户名密码
		String username = request.getParameter("username");
		String password = MD5Utils.md5(request.getParameter("password"));
		User user = userService.login(username, password);
		if (user == null) {
			request.setAttribute("msg", "用户名或密码错误，请重新输入");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// 验证是否激活
		if (user.getState() != 1) {
			request.setAttribute("msg", "用户未激活，请前往邮箱进行激活");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// 30天自动登录
		if ("true".equals(request.getParameter("autologin"))) {
			Cookie autologinC = new Cookie("autologin",
					URLEncoder.encode(user.getUsername() + ":" + user.getPassword(), "utf-8"));
			autologinC.setPath("/");
			autologinC.setMaxAge(3600 * 24 * 30);
			response.addCookie(autologinC);
		}
		// 记住用户名
		if ("true".equals(request.getParameter("remembername"))) {
			Cookie remembernameC = new Cookie("remembername", URLEncoder.encode(user.getUsername(), "utf-8"));
			remembernameC.setPath("/");
			remembernameC.setMaxAge(3600 * 24 * 30);
			response.addCookie(remembernameC);
		} else {
			Cookie remembernameC = new Cookie("remembername", "");
			remembernameC.setPath("/");
			remembernameC.setMaxAge(0);
			response.addCookie(remembernameC);
		}
		// 跳转到主页
		request.getSession().setAttribute("user", user);
		response.sendRedirect("index.jsp");
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
