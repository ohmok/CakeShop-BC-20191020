package com.sikiedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.Page;
import com.sikiedu.service.GoodsService;
import com.sun.media.jfxmedia.track.Track.Encoding;
import com.sun.xml.internal.bind.v2.runtime.output.Encoded;

@WebServlet("/goods_search")
public class GoodsSearchServlet extends HttpServlet {
	private GoodsService gService = new GoodsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword");
		
		if (request.getParameter("pageGo") != null) {
			int pageGo = Integer.parseInt(request.getParameter("pageGo"));
			System.out.println(pageGo);
		}
		
		int pageNo = 1;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		Page page = gService.getSearchGoodsPage(keyword, pageNo);

		request.setAttribute("p", page);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
