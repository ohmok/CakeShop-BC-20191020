package com.sikiedu.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sikiedu.model.Goods;
import com.sikiedu.model.Recommend;
import com.sikiedu.util.DBUtil;

public class GoodsDao {

	public List<Map<String, Object>> getGoodsList(int recommendType) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT g.id,g.name,g.cover,g.price,t.name typename FROM goods g,recommend r,type t WHERE r.type = ? AND r.goods_id = g.id AND g.type_id = t.id";
		return queryRunner.query(sql, new MapListHandler(), recommendType);
	}

	public Map<String, Object> getScrollGoods() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT g.id,g.name,g.cover,g.price FROM goods g,recommend r WHERE r.type = 1 AND r.goods_id = g.id";
		return queryRunner.query(sql, new MapHandler());
	}

	// 分页查询 并返回所有商品
	public List<Goods> selectGoods(int typeId, int pageNo, int pageSize) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		if (typeId == 0) {
			String sql = "SELECT id,name,cover,price FROM goods LIMIT ?,?";
			return queryRunner.query(sql, new BeanListHandler<Goods>(Goods.class), (pageNo - 1) * pageSize, pageSize);
		} else {
			String sql = "SELECT id,name,cover,price FROM goods WHERE type_id = ? LIMIT ?,?";
			return queryRunner.query(sql, new BeanListHandler<Goods>(Goods.class), typeId, (pageNo - 1) * pageSize,
					pageSize);
		}
	}

	// 总记录数 对应selectGoods
	public int getGoodsCount(int typeId) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		if (typeId == 0) {
			String sql = "SELECT COUNT(*) FROM goods";
			return queryRunner.query(sql, new ScalarHandler<Long>()).intValue();
		} else {
			String sql = "SELECT COUNT(*) FROM goods WHERE type_id = ?";
			return queryRunner.query(sql, new ScalarHandler<Long>(), typeId).intValue();
		}

	}

	// 分页查询，recommend.type==0查询所有商品，recommend.type!=0查询[条幅-热销-新品]商品
	public List<Goods> selectGoodsRecommend(int type, int pageNo, int pageSize) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		if (type == 0) {
			String sql = "SELECT g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.name typename FROM goods g,type t WHERE g.type_id = t.id LIMIT ?,?";
			return queryRunner.query(sql, new BeanListHandler<Goods>(Goods.class), (pageNo - 1) * pageSize, pageSize);
		} else {
			String sql = "SELECT g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.name typename FROM goods g,recommend r,type t WHERE g.id = r.goods_id AND g.type_id = t.id AND r.type = ? LIMIT ?,?";
			return queryRunner.query(sql, new BeanListHandler<Goods>(Goods.class), type, (pageNo - 1) * pageSize,
					pageSize);
		}

	}

	// recommend.type==0查询所有商品总数，recommend.type!=0查询[条幅-热销-新品]商品总数
	public int getGoodsRecommendCount(int type) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		if (type == 0) {
			return getGoodsCount(0);
		} else {
			String sql = "SELECT COUNT(*) FROM recommend WHERE type = ?";
			return queryRunner.query(sql, new ScalarHandler<Long>(), type).intValue();
		}
	}

	// 查询商品goods是否是[1-条幅，2-热销，3-新品]
	public boolean isRecommend(int type, Goods goods) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT * FROM recommend WHERE type = ? AND goods_id = ?";
		Recommend r = queryRunner.query(sql, new BeanHandler<Recommend>(Recommend.class), type, goods.getId());
		if (r == null) {
			return false;
		} else {
			return true;
		}
	}

	// 判断是都为新品商品
	public boolean isNew(Goods goods) throws SQLException {
		return isRecommend(3, goods);
	}

	// 判断是都为条幅商品
	public boolean isScroll(Goods goods) throws SQLException {
		return isRecommend(1, goods);
	}

	// 判断是都为热销商品
	public boolean isHot(Goods goods) throws SQLException {
		return isRecommend(2, goods);
	}

	// 添加goodsid商品为type=[1-条幅，2-热销，3-新品]商品
	public void addRecommend(int type, int goodsid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "INSERT INTO recommend(type,goods_id) VALUES(?,?)";
		queryRunner.update(sql, type, goodsid);
	}

	// 移除type=[1-条幅，2-热销，3-新品]商品
	public void removeRecommend(int type, int goodsid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "DELETE FROM recommend WHERE type = ? AND goods_id = ?";
		queryRunner.update(sql, type, goodsid);
	}

	// 根据ID查询产品
	public Goods getById(int id) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.id typeid,t.name typename FROM goods g,type t WHERE g.id = ? AND t.id = g.type_id";
		return queryRunner.query(sql, new BeanHandler<Goods>(Goods.class), id);
	}

	// 模糊搜索总数
	public int getSearchCount(String keyword) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT COUNT(*) FROM goods WHERE name LIKE ?";
		return queryRunner.query(sql, new ScalarHandler<Long>(), "%" + keyword + "%").intValue();

	}

	// 模糊搜索
	public List<Goods> getSearchGoodsPage(String keyword, int pageNo, int pageSize) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "SELECT * FROM cakeshop.goods WHERE name LIKE ? LIMIT ?,?";
		return queryRunner.query(sql, new BeanListHandler<Goods>(Goods.class), "%" + keyword + "%",
				(pageNo - 1) * pageSize, pageSize);

	}

	// 插入商品
	public void insert(Goods goods) throws SQLException {

		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "INSERT INTO goods(name,cover,image1,image2,price,intro,stock,type_id) VALUES(?,?,?,?,?,?,?,?)";
		queryRunner.update(sql, goods.getName(), goods.getCover(), goods.getImage1(), goods.getImage2(),
				goods.getPrice(), goods.getIntro(), goods.getStock(), goods.getType().getId());
	}

	// 修改商品
	public void update(Goods goods) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "INSERT INTO goods(name,cover,image1,image2,price,intro,stock,type_id) VALUES(?,?,?,?,?,?,?,?)";
		sql = "UPDATE goods SET name = ?,cover = ?,image1 = ?,image2 = ?,price = ?,intro = ?,stock = ?,type_id = ? WHERE id = ?";
		queryRunner.update(sql, goods.getName(), goods.getCover(), goods.getImage1(), goods.getImage2(),
				goods.getPrice(), goods.getIntro(), goods.getStock(), goods.getType().getId(), goods.getId());

	}

	// 删除商品
	public void delete(int goodsid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "DELETE FROM goods WHERE id = ?";
		queryRunner.update(sql, goodsid);
	}

}
