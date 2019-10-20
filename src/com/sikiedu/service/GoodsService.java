package com.sikiedu.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sikiedu.dao.GoodsDao;
import com.sikiedu.model.Goods;
import com.sikiedu.model.Page;

public class GoodsService {

	private GoodsDao gDao = new GoodsDao();

	// 热销
	public List<Map<String, Object>> getHotGoodsList() {

		List<Map<String, Object>> list = null;
		try {
			list = gDao.getGoodsList(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 新品
	public List<Map<String, Object>> getNewGoodsList() {

		List<Map<String, Object>> list = null;
		try {
			list = gDao.getGoodsList(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 条幅
	public Map<String, Object> getScrollGoods() {

		Map<String, Object> map = null;
		try {
			map = gDao.getScrollGoods();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public List<Goods> selectGoods(int typeId, int pageNo, int pageSize) {

		List<Goods> list = null;
		try {
			list = gDao.selectGoods(typeId, pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取系列数据，并使用分页控制模型 控制页数，并获取当前页数据
	 * 
	 * @param typeId 系列
	 * @param pageNo 控制页
	 * @return
	 */
	public Page getGoodsPage(int typeId, int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int totalCount = 0;
		try {
			totalCount = gDao.getGoodsCount(typeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		p.setPageSizeAndTotalCount(8, totalCount);

		// 获取页数据
		List<Goods> list = null;
		try {
			list = gDao.selectGoods(typeId, pageNo, 8);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将当前页数据添加到Page模型的List中
		p.setList(list);
		return p;
	}

	/**
	 * 获取类型数据，并使用分页控制模型 控制页数，并获取当前页数据
	 * 
	 * @param 类型     [type 2-热销 ， 3-新品 ]
	 * @param pageNo 控制页
	 * @return
	 */
	public Page selectGoodsRecommend(int type, int pageNo) {

		Page page = new Page();
		page.setPageNo(pageNo);

		int totalCount = 0;
		try {
			totalCount = gDao.getGoodsRecommendCount(type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageSizeAndTotalCount(8, totalCount);

		// 获取页数据
		List<Goods> list = null;
		try {
			list = gDao.selectGoodsRecommend(type, pageNo, 8);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setList(list);

		return page;
	}

	public Goods getById(int id) {

		Goods g = null;
		try {
			g = gDao.getById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}

	public Page getSearchGoodsPage(String keyword, int pageNo) {
		Page page = new Page();
		page.setPageNo(pageNo);
		int totalCount = 0;
		try {
			totalCount = gDao.getSearchCount(keyword);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		page.setPageSizeAndTotalCount(8, totalCount);

		List<Goods> list = null;
		try {
			list = gDao.getSearchGoodsPage(keyword, pageNo, 8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setList(list);

		return page;
	}

	public Page getGoodsRecommendPage(int type, int pageNo) {

		Page page = new Page();
		int pageSize = 8;
		page.setPageNo(pageNo);

		int totalCount = 0;
		try {
			totalCount = gDao.getGoodsRecommendCount(type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setPageSizeAndTotalCount(pageSize, totalCount);

		List<Goods> list = null;
		try {
			list = gDao.selectGoodsRecommend(type, pageNo, pageSize);
			for (Goods goods : list) {
				goods.setScroll(gDao.isScroll(goods));
				goods.setHot(gDao.isHot(goods));
				goods.setNew(gDao.isNew(goods));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setList(list);

		return page;
	}

	// 添加goodsid商品为type=[1-条幅，2-热销，3-新品]商品
	public void addRecommend(int type, int goodsid) {

		try {
			gDao.addRecommend(type, goodsid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 移除type=[1-条幅，2-热销，3-新品]商品
	public void removeRecommend(int type, int goodsid) {

		try {
			gDao.removeRecommend(type, goodsid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 插入商品
	public void insert(Goods goods) {

		try {
			gDao.insert(goods);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 修改商品信息
	public boolean update(Goods goods) {

		try {
			gDao.update(goods);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除商品
	public boolean delete(int goodsid) {

		try {
			gDao.delete(goodsid);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
