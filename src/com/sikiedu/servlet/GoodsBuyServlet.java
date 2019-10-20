package com.sikiedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.Goods;
import com.sikiedu.model.Order;
import com.sikiedu.service.GoodsService;
import com.sikiedu.service.UserService;

@WebServlet("/goods_buy")
public class GoodsBuyServlet extends HttpServlet {
	private GoodsService gService = new GoodsService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 创建订单表对象 ；如果会话中存在该对象则获取 ，不存在则创建一个对象
		Order order = null;
		if (request.getSession().getAttribute("order") != null) {
			order = (Order) request.getSession().getAttribute("order");
		} else {
			order = new Order();
			request.getSession().setAttribute("order", order);
		}
		int goodsid = Integer.parseInt(request.getParameter("goodsid"));
		Goods goods = gService.getById(goodsid);

		if (goods.getStock() > 0) {
			order.addGoods(goods);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");
		}
	}
}
