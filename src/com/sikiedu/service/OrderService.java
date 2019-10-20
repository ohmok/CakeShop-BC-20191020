package com.sikiedu.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sikiedu.dao.OrderDao;
import com.sikiedu.model.Order;
import com.sikiedu.model.OrderItem;
import com.sikiedu.model.Page;
import com.sikiedu.util.DBUtil;

public class OrderService {
	private OrderDao oDao = new OrderDao();

	public void addOrder(Order order) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			// 添加订单
			oDao.insertOrder(conn, order);
			// 获得订单id，并设置
			int id = oDao.getLastInsertId(conn);
			order.setId(id);
			// 添加订单项，需要先获取订单ID
			for (OrderItem item : order.getItemMap().values()) {
				oDao.insertOrderItem(conn, item);
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<Order> selectAll(int userid) {

		List<Order> list = null;
		try {
			list = oDao.selectAll(userid);
			for (Order o : list) {
				List<OrderItem> l = oDao.selectAllItem(o.getId());
				o.setItemList(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Page getOrderPage(int status, int pageNo) {

		int pageSize = 10;
		Page page = new Page();
		page.setPageNo(pageNo);

		int totalCount = 0;
		try {
			totalCount = oDao.getOrderCount(status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageSizeAndTotalCount(pageSize, totalCount);

		List<Order> list = null;
		try {
			list = oDao.selectOrderList(status, pageNo, pageSize);
			for (Order o : list) {
				List<OrderItem> l = oDao.selectAllItem(o.getId());
				o.setItemList(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setList(list);

		return page;
	}

	public void updateStatus(int id, int status) {

		try {
			oDao.updateStatus(id, status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(int id) {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			oDao.deleteOrderItem(conn, id);
			oDao.deleteOrder(conn, id);

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

	}
}
