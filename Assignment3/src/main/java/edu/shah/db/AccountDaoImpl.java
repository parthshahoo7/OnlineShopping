package edu.shah.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import edu.shah.model.Account;
import edu.shah.web.AccountMapper;
import edu.shah.web.WebAccount;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private DataSourceTransactionManager transactionManager;

	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.datasource = datasource;
		this.jdbcTemplate = new JdbcTemplate(this.datasource);
	}

	@Override
	public WebAccount createAccount(final WebAccount webAccount) {
		// TODO Auto-generated method stub
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {

			String SQL = "insert into inventorydb.account(email,password,username,address)" + "values (?, ? , ? , ? )";

			jdbcTemplate.update(SQL, webAccount.getAccount().getEmail(), webAccount.getPassword(),
					webAccount.getAccount().getUserName(), webAccount.getAccount().getAddress());
			String SQL1 = "insert into inventorydb.authority(email,role) values(?,?)";
			for (int i = 0; i < webAccount.getAccount().getAuthority().size(); i++) {
				jdbcTemplate.update(SQL1, webAccount.getAccount().getEmail(),
						webAccount.getAccount().getAuthority().get(i));

			}
			transactionManager.commit(status);
		} catch (DataAccessException e) {
			System.out.println("Error in creating product record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}

		return webAccount;
	}

	@Override
	public int deleteAccount(String email) {
		// TODO Auto-generated method stub
		String SQL = "delete from inventorydb.account where email=?";
		jdbcTemplate.update(SQL, email);
		return 1;
	}

	@Override
	public Account getAccountByEmail(String email) {
		// TODO Auto-generated method stub
		try {
			String SQL = "select a.email as email,a.password as password,a.username as username,a.address as address,string_agg(b.role,',') as role from inventorydb.account as a, inventorydb.authority as b where a.email=b.email and a.email=? group by a.email,a.password,a.address";
			Account account = jdbcTemplate.queryForObject(SQL, new Object[] { email }, new AccountMapper());
			return account;
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		String SQL = "select a.email as email,a.password as password,a.username as username,a.address as address,string_agg(b.role,',') as role from inventorydb.account as a, inventorydb.authority as b where a.email=b.email group by a.email,a.password,a.address";
		List<Account> accounts = jdbcTemplate.query(SQL, new AccountMapper());
		return accounts;
	}

	@Override
	public int update(WebAccount webAccount) {
		// TODO Auto-generated method stub
		String SQL = "update inventorydb.account set (password,,username,address) = (?, ? , ?) where email= ?";
		jdbcTemplate.update(SQL, webAccount.getPassword(), webAccount.getAccount().getUserName(),
				webAccount.getAccount().getAddress(), webAccount.getAccount().getEmail());
		System.out.println("Updated Record with ID=" + webAccount.getAccount().getEmail());
		String SQL1 = "update inventorydb.authority set(role) = (?) where email=?";
		for (int i = 0; i < webAccount.getAccount().getAuthority().size(); i++) {
			jdbcTemplate.update(SQL1, webAccount.getAccount().getEmail(),
					webAccount.getAccount().getAuthority().get(i).toString());
		}
		return 1;
	}

}
