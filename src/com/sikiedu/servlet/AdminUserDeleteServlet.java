package com.sikiedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.service.UserService;

/**
 * Servlet implementation class AdminUserDeleteServlet
 */
@WebServlet("/admin/user_delete")
public class AdminUserDeleteServlet extends HttpServlet {
	private UserService uServie = new UserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		boolean isSuccess = uServie.delete(id);
		if(isSuccess) {
			request.setAttribute("msg", "用户删除成功");
		}else {
			request.setAttribute("failMsg", "该用户还有订单在进行中,不能进行删除！");
		}
		request.getRequestDispatcher("/admin/user_list").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
