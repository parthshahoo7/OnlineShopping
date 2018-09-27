package edu.shah.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.shah.web.CategoryMapper;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.datasource = datasource;
		this.jdbcTemplate = new JdbcTemplate(this.datasource);
	}

	@Override
	public List<String> getAllCategory() {
		String SQL = "Select name from inventorydb.category";
		List<String> category = jdbcTemplate.query(SQL, new CategoryMapper());
		return category;
	}

}
