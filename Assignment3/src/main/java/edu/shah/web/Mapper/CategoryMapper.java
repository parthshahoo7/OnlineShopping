package edu.shah.web.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CategoryMapper implements RowMapper<String> {
	@Override
	public String mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		String category = rs.getString("name");
		return category;
	}
}