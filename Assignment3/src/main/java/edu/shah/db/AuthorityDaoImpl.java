package edu.shah.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.shah.web.Mapper.AuthorityMapper;

@Repository
public class AuthorityDaoImpl implements AuthorityDao {

	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.datasource = datasource;
		this.jdbcTemplate = new JdbcTemplate(this.datasource);
	}

	@Override
	public List<String> getAllRolesByEmail(String email) {
		// TODO Auto-generated method stub
		String SQL = "Select * from inventorydb.authority where email=?";
		List<String> roles = jdbcTemplate.query(SQL, new Object[] { email }, new AuthorityMapper());
		return roles;
	}
}
