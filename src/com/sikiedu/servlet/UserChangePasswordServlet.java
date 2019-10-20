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
 * Servlet implementation class UserChangePasswordServlet
 */
@WebServlet("/user_changepassword")
public class UserChangePasswordServlet extends HttpServlet {
	private UserService uService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");

		String password = request.getParameter("password");
		String passwordNew = request.getParameter("passwordNew");

		if (password.equals(user.getPassword())) {
			request.setAttribute("msg", "修改成功!");
			user.setPassword(passwordNew);
			uService.updateUserPassword(user);
		} else {
			request.setAttribute("failMsg", "原密码马岩,你再谂谂!");
		}
		request.getRequestDispatcher("/user_mycenter.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
