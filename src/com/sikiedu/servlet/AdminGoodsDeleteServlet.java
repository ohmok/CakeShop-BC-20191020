package com.sikiedu.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.Goods;
import com.sikiedu.service.GoodsService;

/**
 * Servlet implementation class AdminGoodsDeleteServlet
 */
@WebServlet("/admin/goods_delete")
public class AdminGoodsDeleteServlet extends HttpServlet {
	private GoodsService gService = new GoodsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int goodsid = Integer.parseInt(request.getParameter("id"));
		boolean isSuccess = gService.delete(goodsid);
		if (isSuccess) {
			request.setAttribute("msg", "删除成功！");
		} else {
			request.setAttribute("failMsg", "该商品订单正在进行中,无法删除！");
		}
		request.getRequestDispatcher("/admin/goods_list").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
