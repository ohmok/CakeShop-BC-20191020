package com.sikiedu.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sikiedu.model.Order;
import com.sikiedu.model.OrderItem;
import com.sikiedu.util.DBUtil;
import com.sun.javafx.iio.common.ScalerFactory;

public class OrderDao {

	public void insertOrder(Connection connection, Order order) throws SQLException {

		QueryRunner queryRunner = new QueryRunner();
		String sql = "INSERT INTO `order`(total,amount,status,paytype,name,phone,address,datetime,user_id) VALUES(?,?,?,?,?,?,?,?,?)";
		queryRunner.update(connection, sql, order.getTotal(), order.getAmount(), order.getStatus(), order.getPaytype(),
				order.getName(), order.getPhone(), order.getAddress(), order.getDatetime(), order.getUser().getId());
	}

	public int getLastInsertId(Connection connection) throws SQLException {

		QueryRunner queryRunner = new QueryRunner();
		String sql = "SELECT last_insert_id()";
		return queryRunner.query(connection, sql, new ScalarHandler<BigInteger>()).intValue();
	}

	public void insertOrderItem(Connection connection, OrderItem item) throws SQLException {

		QueryRunner queryRunner = new QueryRunner();
		String sql = "INSERT INTO orderitem(price,amount,goods_id,order_id) VALUES(?,?,?,?)";
		queryRunner.update(connection, sql, item.getPrice(), item.getAmount(), item.getGoods().getId(),
				item.getOrder().getId());
	}

	public List<Order> selectAll(int userid) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT * FROM `order` WHERE user_id = ? ORDER BY datetime DESC";
		return queryRunner.query(sql, new BeanListHandler<Order>(Order.class), userid);
	}

	public List<OrderItem> selectAllItem(int orderid) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT i.id,g.name,i.price,i.amount FROM goods g,orderitem i WHERE i.goods_id = g.id AND i.order_id = ?";
		return queryRunner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), orderid);
	}

	public int getOrderCount(int status) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "";
		if (status == 0) {
			sql = "SELECT COUNT(*) FROM `order`";
			return queryRunner.query(sql, new ScalarHandler<Long>()).intValue();
		} else {
			sql = "SELECT COUNT(*) FROM `order` WHERE status = ?";
			return queryRunner.query(sql, new ScalarHandler<Long>(), status).intValue();
		}
	}

	public List<Order> selectOrderList(int status, int pageNo, int pageSize) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "";
		if (status == 0) {
			sql = "SELECT o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username FROM `order` o,users u WHERE o.user_id = u.id ORDER BY datetime DESC LIMIT ?,?;";
			return queryRunner.query(sql, new BeanListHandler<Order>(Order.class), (pageNo - 1) * pageSize, pageSize);
		} else {
			sql = "SELECT o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username FROM `order` o,users u WHERE o.user_id = u.id AND o.status = ? ORDER BY datetime DESC LIMIT ?,?";
			return queryRunner.query(sql, new BeanListHandler<Order>(Order.class), status, (pageNo - 1) * pageSize,
					pageSize);
		}
	}

	public void updateStatus(int id, int status) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "UPDATE `order` SET status = ? WHERE id = ?";
		queryRunner.update(sql, status, id);
	}

	public void deleteOrder(Connection conn, int id) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "DELETE FROM `order` WHERE id = ?";
		queryRunner.update(conn, sql, id);
	}

	public void deleteOrderItem(Connection conn, int id) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "DELETE FROM `orderitem` WHERE order_id = ?";
		queryRunner.update(conn, sql, id);
	}
}
