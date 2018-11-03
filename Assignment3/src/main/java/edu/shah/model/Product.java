package edu.shah.model;

import java.util.ArrayList;

public class Product {

	private int id;
	private String name;
	private String description;
	private Object category;
	private String cost;
	private ArrayList<Object> location;
	private String discount;

	public Product(int id, String name, String description, Category category, String cost, ArrayList<Object> location,
			String discount) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.cost = cost;
		this.location = location;
		this.discount = discount;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getCategory() {
		return category;
	}

	public void setCategory(Object category) {
		this.category = category;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public ArrayList<Object> getLocation() {
		return location;
	}

	public void setLocation(ArrayList<Object> object) {
		this.location = object;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
}