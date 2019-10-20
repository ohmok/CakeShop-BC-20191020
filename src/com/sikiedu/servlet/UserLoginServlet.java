package com.sikiedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.User;
import com.sikiedu.service.UserService;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/user_login")
public class UserLoginServlet extends HttpServlet {
	private UserService uService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String usernameOrEmail = request.getParameter("usernameOrEmail");
		String password = request.getParameter("password");

		User user = uService.login(usernameOrEmail, password);

		if (user != null) {
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/user_mycenter.jsp").forward(request, response);
		} else {
			request.setAttribute("failMsg", "登录失败,请重新登录");
			request.getRequestDispatcher("/user_login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
