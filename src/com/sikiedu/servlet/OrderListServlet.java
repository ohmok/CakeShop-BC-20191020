package com.sikiedu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.Order;
import com.sikiedu.model.OrderItem;
import com.sikiedu.model.User;
import com.sikiedu.service.OrderService;

@WebServlet("/order_list")
public class OrderListServlet extends HttpServlet {
	private OrderService oService = new OrderService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		List<Order> list = oService.selectAll(user.getId());
		request.setAttribute("orderList", list);
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
