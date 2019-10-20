package com.sikiedu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sikiedu.util.PriceUtil;

public class Order {

	private int id;
	private float total;// 总价
	private int amount;// 商品总数
	private int status;// 订单状态-[1未支付/2已支付/3配送中/4已完成]
	private int paytype;// 支付方式-[1微信/2支付宝/3货到付款]
	private String name;// 收货人
	private String phone;// 收货电话
	private String address;// 收货地址
	private Date datetime;// 下单时间
	private User user;// 下单用户
	private Map<Integer, OrderItem> itemMap = new HashMap<Integer, OrderItem>();
	private List<OrderItem> itemList = new ArrayList<OrderItem>();

	public Order() {
		super();
	}

	public List<OrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	// 添加商品
	public void addGoods(Goods goods) {

		if (itemMap.containsKey(goods.getId())) {
			OrderItem item = itemMap.get(goods.getId());
			item.setAmount(item.getAmount() + 1);
		} else {
			OrderItem item = new OrderItem(goods.getPrice(), 1, goods, this);
			itemMap.put(goods.getId(), item);
		}
		amount += 1;
		total = PriceUtil.add(total, goods.getPrice());
	}

	// 减少商品
	public void lessen(int goodsid) {

		if (itemMap.containsKey(goodsid)) {
			OrderItem item = itemMap.get(goodsid);
			item.setAmount(item.getAmount() - 1);
			total = PriceUtil.substract(total, item.getPrice());
			amount -= 1;
			if (item.getAmount() <= 0) {
				itemMap.remove(goodsid);
			}
		}
	}

	// 删除商品
	public void deletes(int goodsid) {

		if (itemMap.containsKey(goodsid)) {
			OrderItem item = itemMap.get(goodsid);
			amount -= item.getAmount();
			total = PriceUtil.substract(total, item.getAmount() * item.getPrice());
			itemMap.remove(goodsid);
		}
	}

	public Map<Integer, OrderItem> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<Integer, OrderItem> itemMap) {
		this.itemMap = itemMap;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPaytype() {
		return paytype;
	}

	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUsername(String username) {
		this.user = new User();
		user.setUsername(username);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", total=" + total + ", amount=" + amount + ", status=" + status + ", paytype="
				+ paytype + ", name=" + name + ", phone=" + phone + ", address=" + address + ", datetime=" + datetime
				+ ", user=" + user + ", itemMap=" + itemMap + ", itemList=" + itemList + "]";
	}

}
