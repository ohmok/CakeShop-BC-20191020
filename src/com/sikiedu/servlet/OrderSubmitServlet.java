package com.sikiedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.Order;
import com.sikiedu.model.OrderItem;

/**
 * Servlet implementation class OrderSubmitServlet
 */
@WebServlet("/order_submit")
public class OrderSubmitServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("user") != null) {
			Order item = (Order) request.getSession().getAttribute("order");
			request.getRequestDispatcher("/order_submit.jsp").forward(request, response);
		} else {
			request.setAttribute("failMsg", "请登录后,再提交订单！");
			request.getRequestDispatcher("/user_login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
