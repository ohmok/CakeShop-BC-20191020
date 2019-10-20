package com.sikiedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.Page;
import com.sikiedu.service.GoodsService;

@WebServlet("/admin/goods_list")
public class AdminGoodsListServlet extends HttpServlet {
	private GoodsService gService = new GoodsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int type = 0;
		if (request.getParameter("type") != null) {
			type = Integer.parseInt(request.getParameter("type"));
		}

		int pageNo = 1;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		Page page = gService.getGoodsRecommendPage(type, pageNo);

		request.setAttribute("p", page);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/admin/goods_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
