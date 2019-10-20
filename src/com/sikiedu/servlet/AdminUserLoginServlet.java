package com.sikiedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.User;
import com.sikiedu.service.UserService;

@WebServlet("/admin_login")
public class AdminUserLoginServlet extends HttpServlet {
	private UserService uService = new UserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = uService.login(username, password);
		if (user != null) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/admin/admin_index.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin_login.jsp");
		}
	}
}
