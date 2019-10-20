package com.sikiedu.service;

import java.sql.SQLException;
import java.util.List;

import com.sikiedu.dao.UserDao;
import com.sikiedu.model.Page;
import com.sikiedu.model.User;

public class UserService {

	private UserDao uDao = new UserDao();

	// 用户注册
	public boolean register(User user) {

		try {
			if (uDao.isUsernameExist(user.getUsername())) {
				return false;
			}
			if (uDao.isEmailExist(user.getEmail())) {
				return false;
			}
			uDao.addUser(user);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 登录验证
	public User login(String usernameOrEmail, String password) {

		User user = null;

		// 验证用户名与密码
		try {
			user = uDao.selectByUsernamePassword(usernameOrEmail, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user != null) {
			return user;
		}

		// 验证邮箱与密码
		try {
			user = uDao.selectByEmailPassword(usernameOrEmail, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user != null) {
			return user;
		}

		// 用户名与邮箱验证失败-返回一个空值User
		return null;
	}

	// 修改收货信息
	public void updateUserAddress(User user) {

		try {
			uDao.updateUserAddress(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 修改密码
	public void updateUserPassword(User user) {

		try {
			uDao.updateUserPassword(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Page getUserPage(int pageNo) {

		int pageSize = 7;
		Page page = new Page();
		page.setPageNo(pageNo);

		int totalCount = 0;
		try {
			totalCount = uDao.selectUserCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageSizeAndTotalCount(pageSize, totalCount);

		List<User> list = null;
		try {
			list = uDao.selectUserList(pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setList(list);

		return page;
	}

	public User selectById(int id) {

		User user = null;
		try {
			user = uDao.selectById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean delete(int id) {

		try {
			uDao.delete(id);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
