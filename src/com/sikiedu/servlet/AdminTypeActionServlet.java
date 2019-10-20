package com.sikiedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.model.Type;
import com.sikiedu.service.TypeService;

@WebServlet("/admin/type_action")
public class AdminTypeActionServlet extends HttpServlet {
	private TypeService tService = new TypeService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("state") != null) {
			// 按钮状态功能[0-修改,1-删除]
			int state = Integer.parseInt(request.getParameter("state"));
			int typeid = Integer.parseInt(request.getParameter("id"));

			if (state == 0) {
				// 获取当前类目数据并转发到edit修改页面
				request.setAttribute("type", tService.select(typeid));
				request.getRequestDispatcher("/admin/type_edit.jsp").forward(request, response);
			}
			if (state == 1) {
				// 删除当前类目并回到当前页
				boolean isSuccess = tService.delete(typeid);
				if (isSuccess) {
					request.setAttribute("msg", "删除成功！");
				} else {
					request.setAttribute("failMsg", "类目下包含商品,无法直接删除！");
				}
				request.getRequestDispatcher("/admin/type_list").forward(request, response);
			}

		} else {
			// 添加类目
			tService.insert(request.getParameter("name"));
			request.getRequestDispatcher("/admin/type_list").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
