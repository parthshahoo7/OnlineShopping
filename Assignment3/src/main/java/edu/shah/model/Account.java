package edu.shah.model;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

public class Account {
	@NotNull
	private String email;
	@NotNull
	private String userName;
	@NotNull
	private String address;
	@NotNull
	private ArrayList<String> authority;

	public Account(String email, String userName, String address, ArrayList<String> authority) {
		super();
		this.email = email;
		this.userName = userName;
		this.address = address;
		this.authority = authority;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<String> getAuthority() {
		return authority;
	}

	public void setAuthority(ArrayList<String> authority) {
		this.authority = authority;
	}

}
