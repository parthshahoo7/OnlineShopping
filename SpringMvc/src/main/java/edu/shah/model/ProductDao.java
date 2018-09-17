package edu.shah.model;

import java.util.ArrayList;

public class ProductDao {

	Product product;
	ArrayList<Product> products = new ArrayList<>();
	ArrayList<String> location = new ArrayList<>();
	ArrayList<String> location1 = new ArrayList<>();
	ArrayList<String> location2 = new ArrayList<>();
	// Here I create this multiple objects only for the following dummies.

	public ProductDao() {
		super();
		location.add("WareHouse1");
		location1.add("WareHouse2");
		location2.add("WareHouse3");
		products.add(new Product(101, "HP", "Best Laptop Brand", "ELECTRONIC", 500, location, true));
		products.add(new Product(102, "Java The Complete Reference", "Perfect Book for Beginer", "BOOK", 20, location2,
				false));
		products.add(
				new Product(103, "AMERICAN EAGLE", "Popular Brand for fashion clothes", "CLOTH", 20, location1, true));
		products.add(new Product(104, "VIZIO", "Popular Television Brand ", "ELECTRONIC", 220, location, false));
	}

	public ArrayList<Product> getAllProducts() {
		return products;
	}

	public Product getProductById(int id) {

		for (Product product : getAllProducts()) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

	public void addProduct(Product product) {
		products.add(product);
	}
}
