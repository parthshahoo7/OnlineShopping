package edu.shah.model;

public class LineItem {
	private Product product;
	private OrderItem orderItem;

	public LineItem() {
		super();
		// TODO Auto-generated constructor stub
		product = new Product();
		orderItem = new OrderItem();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
}