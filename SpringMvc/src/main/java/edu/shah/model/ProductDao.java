package edu.shah.model;

import java.util.ArrayList;

public class ProductDao {

	ArrayList<Product> products = new ArrayList<>();

	public ArrayList<Product> getAllProducts() {
		products.add(new Product(101, "HP", "Best Laptop Brand", "ELECTRONIC", "$500.00", "WareHouse1", true));
		products.add(new Product(102, "Java The Complete Reference", "Perfect Book for Beginer", "BOOK", "$20.00",
				"WareHouse2", false));
		products.add(new Product(103, "AMERICAN EAGLE", "Popular Brand for fashion clothes", "CLOTH", "$20.00",
				"WareHouse2", true));
		products.add(
				new Product(104, "VIZIO", "Popular Television Brand ", "ELECTRONIC", "$220.00", "WareHouse3", false));
		return products;
	}

	public Product getProductById(int id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}
}
