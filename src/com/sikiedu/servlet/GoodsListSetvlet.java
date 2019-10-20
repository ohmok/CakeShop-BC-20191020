package com.sikiedu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.Page;
import com.sikiedu.model.Type;
import com.sikiedu.service.GoodsService;
import com.sikiedu.service.TypeService;

/**
 * Servlet implementation class GoodsListSetvlet
 */
@WebServlet("/goods_list")
public class GoodsListSetvlet extends HttpServlet {

	private GoodsService gService = new GoodsService();
	private TypeService tService = new TypeService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = 0;// 默认ID为0，获取所有系列
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}

		int pageNo = 1;// 默认页
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		// 单独获取数据
//		List<Goods> list = gService.selectGoods(id, pageNo, 8);
//		request.setAttribute("list", list);
		// 获取数据并进行分页
		Page p = gService.getGoodsPage(id, pageNo);
		request.setAttribute("p", p);
		request.setAttribute("id", id);

		Type type = null;
		if (id != 0) {
			type = tService.select(id);
		}

		request.setAttribute("type", type);
		request.getRequestDispatcher("/goods_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
