package com.sikiedu.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sikiedu.model.Order;
import com.sikiedu.model.User;
import com.sikiedu.service.OrderService;

/**
 * Servlet implementation class OrderConfirmServlet
 */
@WebServlet("/order_confirm")
public class OrderConfirmServlet extends HttpServlet {
	private OrderService oService = new OrderService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Order order = (Order) request.getSession().getAttribute("order");
		try {
			BeanUtils.copyProperties(order, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		order.setStatus(2);
		order.setDatetime(new Date());
		order.setUser((User) request.getSession().getAttribute("user"));
		oService.addOrder(order);
		request.getSession().removeAttribute("order");

		request.setAttribute("msg", "订单支付成功！");
		request.getRequestDispatcher("/order_success.jsp").forward(request, response);
	}

}
