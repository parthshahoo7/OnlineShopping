package edu.shah.web.webModel;

import java.util.ArrayList;

import edu.shah.model.Account;

public class WebAccount {
	private String password;
	private String confimPassword;
	private Account account;

	public WebAccount(String password, String confimPassword, Account account) {
		super();
		this.password = password;
		this.confimPassword = confimPassword;
		this.account = account;
	}

	public WebAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfimPassword() {
		return confimPassword;
	}

	public void setConfimPassword(String confimPassword) {
		this.confimPassword = confimPassword;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public final static ArrayList<String> getAllRoles() {
		ArrayList<String> list = new ArrayList<>();
		list.add("ROLE_ADMIN");
		list.add("ROLE_EMP");
		list.add("ROLE_CUSTOMER");
		return list;
	}

	public boolean passwordMatch() {
		if (password.equals(confimPassword))
			return true;
		else
			return false;
	}
}