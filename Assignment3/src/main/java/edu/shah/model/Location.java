package edu.shah.model;

public class Location {
	private int id;
	private String location;

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(int id, String location) {
		super();
		this.id = id;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}