package edu.shah.web.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class LocationMapper implements RowMapper<String> {
	@Override
	public String mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		String location = rs.getString("location");
		return location;
	}
}