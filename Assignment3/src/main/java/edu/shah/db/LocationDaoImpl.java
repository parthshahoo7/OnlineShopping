package edu.shah.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.shah.web.LocationMapper;

@Repository
public class LocationDaoImpl implements LocationDao {

	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.datasource = datasource;
		this.jdbcTemplate = new JdbcTemplate(this.datasource);
	}

	@Override
	public List<String> getAllLocation() {
		String SQL = "Select location from inventorydb.warehouse";
		List<String> location = jdbcTemplate.query(SQL, new LocationMapper());
		return location;
	}
}
