package com.joshua.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joshua.domain.User;
import com.joshua.factory.BasicFactory;
import com.joshua.service.UserService;

public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		// 只有未登录的用户才自动登录
		if (request.getSession(false) == null || request.getSession().getAttribute("user") == null) {
			// 只有呆了自动登录cookie的用户才自动登录
			Cookie[] cookies = request.getCookies();
			Cookie cookie = null;
			if (cookies != null) {
				for (Cookie item : cookies) {
					if ("autologin".equals(item.getName())) {
						cookie = item;
						break;
					}
				}
			}
			if (cookie != null) {
				// 只有自动登录cookie中用户名密码正确的用户才自动登录
				String s = URLDecoder.decode(cookie.getValue(), "utf-8");
				String username = s.split(":")[0];
				String password = s.split(":")[1];
				UserService userService = BasicFactory.getFactory().getInstance(UserService.class);
				User user = userService.login(username, password);
				if (user != null) {
					request.getSession().setAttribute("user", user);
				}
			}
		}
		// 无论是否自动登录都要放行
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
