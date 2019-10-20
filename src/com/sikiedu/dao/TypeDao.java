package com.sikiedu.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sikiedu.model.Type;
import com.sikiedu.util.DBUtil;

public class TypeDao {

	public List<Type> selectAll() throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT id,name FROM type";
		return queryRunner.query(sql, new BeanListHandler<Type>(Type.class));
	}

	public Type select(int id) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT * FROM type WHERE id = ?";
		return queryRunner.query(sql, new BeanHandler<Type>(Type.class), id);
	}

	public void insert(String name) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "INSERT INTO type(name) VALUES(?)";
		queryRunner.update(sql, name);
	}

	public void delete(int id) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "DELETE FROM type WHERE id = ?";
		queryRunner.update(sql, id);
	}

	public void update(int id, String name) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "UPDATE type SET name = ?  WHERE id = ?";
		queryRunner.update(sql, name, id);
	}

}
