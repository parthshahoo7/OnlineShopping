package edu.shah.model;

import java.util.ArrayList;

public class Product {

	private int id;
	private String name;
	private String description;
	private String category;
	private int cost;
	// private String location;
	private ArrayList<String> location;
	private Boolean discount;

	/*
	 * NOTE: Here I change the location attribute to ArrayList<Location> but the
	 * problem that i faced is i don't get the value it print the objects in the
	 * location field. like as follow:
	 * 							 edu.shah.model.Location@1fedbe67
	 * 
	 * Due to this problem instead of using Location class,i use ArrayList<String>
	 * which print the proper value.
	 * 
	 * 
	 * 
	 * 
	 */

	public Product(int id, String name, String description, String category, int cost, ArrayList<String> location,
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public ArrayList<String> getLocation() {
		return location;
	}

	public void setLocation(ArrayList<String> location) {
		this.location = location;
	}

	/*
	 * public String getLocation() { return location; }
	 * 
	 * public void setLocation(String location) { this.location = location; }
	 * 
	 */

	public Boolean getDiscount() {
		return discount;
	}

	public void setDiscount(Boolean discount) {
		this.discount = discount;
	}

}
