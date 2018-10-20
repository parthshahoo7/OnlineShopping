package edu.shah.model;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class ShoppingCart {
	private ArrayList<Product> products;
	private int quantity;

	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
		products = new ArrayList<Product>();
	}

	public void addProducts(Product product) {
		products.add(product);
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public void clear() {
		products.clear();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}