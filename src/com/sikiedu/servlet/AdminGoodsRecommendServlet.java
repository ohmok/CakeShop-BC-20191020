package com.sikiedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.service.GoodsService;

@WebServlet("/admin/recommend")
public class AdminGoodsRecommendServlet extends HttpServlet {
	private GoodsService gService = new GoodsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 状态功能 [0-移除|1-添加]
		int state = Integer.parseInt(request.getParameter("state"));
		// 添加或移除recommend类型[1-条幅|2-热销|3-新品]
		int type = Integer.parseInt(request.getParameter("typeTarget"));
		// 当前商品id
		int goodsid = Integer.parseInt(request.getParameter("id"));
		
		if (state == 0) {
			gService.removeRecommend(type, goodsid);
		} else {
			gService.addRecommend(type, goodsid);
		}
		request.getRequestDispatcher("/admin/goods_list").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
