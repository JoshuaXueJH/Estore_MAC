package com.joshua.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodeFilter implements Filter {
	private FilterConfig filterConfig = null;
	private String defaultEncode = "utf-8";
	private String encode = null;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;

		encode = filterConfig.getInitParameter("encode");
		if (encode == null) {
			encode = defaultEncode;
		}

		req.setCharacterEncoding(encode);
		res.setCharacterEncoding(encode);
		res.setContentType("text/html;charset=" + encode);
		arg2.doFilter(new MyCharacterEncodingRequest(req), res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = arg0;
	}

	class MyCharacterEncodingRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request = null;
		private Boolean isEncode = true;

		public MyCharacterEncodingRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			try {
				if (request.getMethod().equalsIgnoreCase("post")) {
					return request.getParameterMap();
				} else if (request.getMethod().equalsIgnoreCase("get")) {
					Map<String, String[]> map = request.getParameterMap();
					if (isEncode) {
						for (Map.Entry<String, String[]> entry : map.entrySet()) {
							String[] vs = entry.getValue();
							for (int i = 0; i < vs.length; i++) {
								vs[i] = new String(vs[i].getBytes("iso8859-1"), encode);
							}
						}
						isEncode = false;
					}
					return map;
				} else {
					return super.getParameterMap();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		@Override
		public String getParameter(String name) {
			return getParameterValues(name) == null ? null : getParameterValues(name)[0];
		}

		@Override
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		}
	}

}
