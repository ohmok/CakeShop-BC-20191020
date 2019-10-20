package com.sikiedu.model;

public class OrderItem {

	private int id;
	private float price;// 购买时价格
	private int amount;// 订单数量
	private String name;// 产品名
	private Goods goods;// 产品
	private Order order;// 订单,order_id

	public OrderItem() {
		super();
	}

	public OrderItem(float price, int amount, Goods goods, Order order) {
		super();
		this.price = price;
		this.amount = amount;
		this.goods = goods;
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", price=" + price + ", amount=" + amount + ", name=" + name + ", goods=" + goods
				+ ", order=" + order + "]";
	}

}
