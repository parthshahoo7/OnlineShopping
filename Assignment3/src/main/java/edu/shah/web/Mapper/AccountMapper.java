package edu.shah.web.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import edu.shah.model.Account;

public class AccountMapper implements RowMapper<Account> {
	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Account account = new Account();
		ArrayList<String> roles = new ArrayList<>();
		// TODO Auto-generated method stub
		account.setEmail(rs.getString("email"));
		account.setUserName(rs.getString("username"));
		account.setAddress(rs.getString("address"));
		roles.add(rs.getObject("role").toString());
		account.setAuthority(roles);
		return account;
	}
}