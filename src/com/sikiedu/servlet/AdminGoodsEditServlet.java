package com.sikiedu.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sikiedu.model.Goods;
import com.sikiedu.service.GoodsService;

@WebServlet("/admin/goods_edit")
public class AdminGoodsEditServlet extends HttpServlet {
	private GoodsService gService = new GoodsService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> list = upload.parseRequest(request);
			Goods goods = null;
			int pageNo = 1;
			int type = 0;
			for (FileItem item : list) {

				// 判断itme;true-为普通输入项,false-为文件
				if (item.isFormField()) {

					// 设置商品属性
					switch (item.getFieldName()) {
					case "id":
						goods = gService.getById(Integer.parseInt(item.getString("utf-8")));
						break;
					case "pageNo":
						pageNo = Integer.parseInt(item.getString("utf-8"));
						break;
					case "type":
						type = Integer.parseInt(item.getString("utf-8"));
						break;
					case "name":
						goods.setName(item.getString("utf-8"));
						break;
					case "price":
						goods.setPrice(Float.parseFloat(item.getString("utf-8")));
						break;
					case "intro":
						goods.setIntro(item.getString("utf-8"));
						break;
					case "stock":
						goods.setStock(Integer.parseInt(item.getString("utf-8")));
						break;
					case "typeId":
						goods.setTypeid(Integer.parseInt(item.getString("utf-8")));
						break;
					}
				} else {

					if (item.getInputStream().available() <= 0) {
						continue;
					}
					// 1-获取文件名的后缀名；2-根据毫秒值和后缀名设置新的文件名；3-创建保存路径
					String fileName = item.getName().substring(item.getName().indexOf("."));
					fileName = "/" + System.currentTimeMillis() + fileName;
					String path = this.getServletContext().getRealPath("/picture") + fileName;

					// 读取上传
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(path);
					byte[] buff = new byte[1024];
					int len = 0;
					while ((len = in.read(buff)) > 0) {
						out.write(buff, 0, len);
					}
					in.close();
					out.close();

					// 设置商品属性
					switch (item.getFieldName()) {
					case "cover":
						goods.setCover("/picture" + fileName);
						break;
					case "image1":
						goods.setImage1("/picture" + fileName);
						break;
					case "image2":
						goods.setImage2("/picture" + fileName);
						break;
					}
				}
				item.delete();
			}
			boolean isSuccess = gService.update(goods);
			if (isSuccess) {
				request.setAttribute("msg", "商品修改成功！");

			} else {
				request.setAttribute("failMsg", "错误-商品修改失败！");
			}
			request.getRequestDispatcher("/admin/goods_list?pageNo=" + pageNo + "&type=" + type).forward(request,
					response);

		} catch (FileUploadException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
