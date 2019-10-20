package com.sikiedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sikiedu.service.TypeService;

@WebServlet("/admin/type_edit")
public class AdminTypeEditServlet extends HttpServlet {
	private TypeService tService = new TypeService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		tService.update(id, name);
		request.getRequestDispatcher("/admin/type_list").forward(request, response);

	}

}
