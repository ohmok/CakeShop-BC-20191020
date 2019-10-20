package com.sikiedu.service;

import java.sql.SQLException;
import java.util.List;

import com.sikiedu.dao.TypeDao;
import com.sikiedu.model.Type;

public class TypeService {

	private TypeDao tDao = new TypeDao();

	public List<Type> selectAll() {

		List<Type> selectAll = null;
		try {
			selectAll = tDao.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectAll;
	}

	public Type select(int id) {

		Type t = null;
		try {
			t = tDao.select(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	public void insert(String name) {

		try {
			tDao.insert(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean delete(int id) {

		try {
			tDao.delete(id);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void update(int id, String name) {

		try {
			tDao.update(id, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
