package edu.shah.model;

public class Product {

	private int id;
	private String name;
	private String description;
	private String category;
	private String cost;
	private String location;
	private Boolean discount;

	public Product(int id, String name, String description, String category, String cost, String location,
			Boolean discount) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.cost = cost;
		this.location = location;
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}

	public String getCost() {
		return cost;
	}

	public String getLocation() {
		return location;
	}

	public Boolean getDiscount() {
		return discount;
	}

}
