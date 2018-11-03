package edu.shah.web.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import edu.shah.model.Product;

public class ProductMapper implements RowMapper<Product> {
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		ArrayList<Object> location = new ArrayList<>();
		// TODO Auto-generated method stub
		product.setId(rs.getInt("p_id"));
		product.setName(rs.getString("p_name"));
		product.setDescription(rs.getString("p_desc"));
		product.setCategory(rs.getObject("c_name"));
		product.setCost(rs.getString("p_cost"));
		product.setDiscount(rs.getString("p_discount"));
		location.add(rs.getObject("l_location"));
		product.setLocation(location);
		return product;
	}
}