package edu.shah.web;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import edu.shah.model.PurchaseOrder;

public class PurchaseOrderMapper implements RowMapper<PurchaseOrder> {

	@Override
	public PurchaseOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		ArrayList<String> quantities = new ArrayList<>();
		ArrayList<String> prod_names = new ArrayList<>();
		purchaseOrder.setCustomer_name(rs.getString("customer_name"));
		purchaseOrder.setPo_date(rs.getString("po_date"));
		purchaseOrder.setPo_id(rs.getLong("po_id"));
		prod_names.add(rs.getString("prod_name"));
		purchaseOrder.setProd_name(prod_names);
		quantities.add(rs.getString("quantity"));
		purchaseOrder.setQuantity(quantities);
		purchaseOrder.setTotalCost(rs.getString("total_cost"));
		return purchaseOrder;
	}

}
