package edu.shah.web.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AuthorityMapper implements RowMapper<String> {
	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		String role = rs.getString("role");
		return role;
	}
}