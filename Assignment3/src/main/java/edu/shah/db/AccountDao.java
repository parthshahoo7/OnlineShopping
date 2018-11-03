package edu.shah.db;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.shah.model.Account;
import edu.shah.web.webModel.WebAccount;

@Repository
public interface AccountDao {
	public WebAccount createAccount(WebAccount webAccount);

	public int deleteAccount(Account account);

	public Account getAccountByEmail(String email);

	public List<Account> getAllAccount();

	public int update(WebAccount webAccount);
}
