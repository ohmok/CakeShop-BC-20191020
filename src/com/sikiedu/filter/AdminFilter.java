package com.sikiedu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.User;

public class AdminFilter implements Filter {

	public AdminFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 获取当前登录的用户
		User user = (User) req.getSession().getAttribute("user");
		// 如果已登录 并且是 管理员则放行
		if (user != null && user.isIsadmin()) {
			chain.doFilter(request, response);
		} else {
			// 非管理员重定向到登录页
			resp.sendRedirect(req.getContextPath() + "/admin_login.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
