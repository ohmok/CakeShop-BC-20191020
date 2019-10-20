package com.sikiedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.Goods;
import com.sikiedu.model.Page;
import com.sikiedu.service.GoodsService;

@WebServlet("/goodsRecommend_list")
public class GoodsRecommendListServlet extends HttpServlet {
	private GoodsService gService = new GoodsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int typeid = Integer.parseInt(request.getParameter("typeid"));

		int pageNo = 1;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		Page page = gService.selectGoodsRecommend(typeid, pageNo);

		request.setAttribute("p", page);
		request.setAttribute("typeid", typeid);

		request.getRequestDispatcher("/goodsRecommend_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
