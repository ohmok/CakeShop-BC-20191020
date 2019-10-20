package com.sikiedu.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sikiedu.model.User;
import com.sikiedu.util.DBUtil;

public class UserDao {

	// 添加用户
	public int addUser(User user) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "INSERT INTO users(username,email,password,name,phone,address,isadmin,isvalidate) VALUES(?,?,?,?,?,?,?,?);";
		return queryRunner.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), user.getName(),
				user.getPhone(), user.getAddress(), user.isIsadmin(), user.isIsisvalidate());
	}

	// 验证用户名
	public boolean isUsernameExist(String username) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT * FROM users WHERE username = ?";
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class), username);

		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	// 验证邮箱
	public boolean isEmailExist(String email) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT * FROM users WHERE email = ?";
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class), email);

		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	// 登录验证-用户名
	public User selectByUsernamePassword(String username, String password) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		return queryRunner.query(sql, new BeanHandler<User>(User.class), username, password);
	}

	// 登录验证-邮箱
	public User selectByEmailPassword(String email, String password) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
		return queryRunner.query(sql, new BeanHandler<User>(User.class), email, password);
	}

	// 修改收货信息
	public void updateUserAddress(User user) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "UPDATE users SET name = ?, phone = ?, address = ? WHERE id = ?";
		queryRunner.update(sql, user.getName(), user.getPhone(), user.getAddress(), user.getId());
	}

	// 修改密码
	public void updateUserPassword(User user) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "UPDATE users SET password = ? WHERE id = ?";
		queryRunner.update(sql, user.getPassword(), user.getId());

	}

	// 用户总数
	public int selectUserCount() throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT COUNT(*) FROM users";
		return queryRunner.query(sql, new ScalarHandler<Long>()).intValue();
	}

	// 分页查询用户
	public List<User> selectUserList(int pageNo, int pageSize) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT * FROM users LIMIT ?,?";
		return queryRunner.query(sql, new BeanListHandler<User>(User.class), (pageNo - 1) * pageSize, pageSize);
	}

	// 根据id查询用户
	public User selectById(int id) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT * FROM users WHERE id = ?";
		return queryRunner.query(sql, new BeanHandler<User>(User.class), id);
	}

	// 根据ID删除用户
	public void delete(int id) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "DELETE FROM users WHERE id = ?";
		queryRunner.update(sql, id);

	}

}
