package edu.shah.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import edu.shah.model.PurchaseOrder;
import edu.shah.web.PurchaseOrderMapper;

@Repository
public class PurchaseOrderDaoImpl implements PurchaseOrderDao {

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
	public PurchaseOrder createOrder(PurchaseOrder purchaseOrder) {
		// TODO Auto-generated method stub
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {

			String SQL = "insert into inventorydb.purchase_order(customer_name,po_date,total_cost) values(? , ? , ? )";
			KeyHolder key = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub
					PreparedStatement preparedStatement = con.prepareStatement(SQL, new String[] { "po_id" });
					preparedStatement.setString(1, purchaseOrder.getCustomer_name());
					preparedStatement.setDate(2, Date.valueOf(purchaseOrder.getPo_date()));
					preparedStatement.setString(3, purchaseOrder.getTotalCost());
					return preparedStatement;
				}
			}, key);
			purchaseOrder.setPo_id(Long.parseLong(key.getKey().toString()));
			String SQL1 = "insert into inventorydb.order_detail values(?,?,?,?)";

			for (int i = 0; i < purchaseOrder.getProd_name().size(); i++) {
				jdbcTemplate.update(SQL1, purchaseOrder.getPo_id(), i + 1, purchaseOrder.getProd_name().get(i),
						purchaseOrder.getQuantity().get(i));
			}
			transactionManager.commit(status);
		} catch (DataAccessException e) {
			System.out.println("Error in creating product record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}
		return null;
	}

	@Override
	public int deleteOrder(PurchaseOrder purchaseOrder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PurchaseOrder> getAllOrders() {
		// TODO Auto-generated method stub
		String SQL = "select p.po_id as po_id,line_no,customer_name,po_date,prod_name,quantity,total_cost from inventorydb.purchase_order as p,inventorydb.order_detail as o where p.po_id=o.po_id group by o.po_id,customer_name,po_date,total_cost,prod_name,quantity,line_no,p.po_id order by p.po_id";
		List<PurchaseOrder> purchaserOrders = jdbcTemplate.query(SQL, new PurchaseOrderMapper());
		return purchaserOrders;
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderById(Long id) {
		// TODO Auto-generated method stub
		String SQL = "select p.po_id as po_id,line_no,customer_name,po_date,prod_name,quantity,total_cost from inventorydb.purchase_order as p,inventorydb.order_detail as o where p.po_id=o.po_id and o.po_id=? group by o.po_id,customer_name,po_date,total_cost,prod_name,quantity,line_no,p.po_id order by p.po_id ";
		List<PurchaseOrder> purchaseOrders = jdbcTemplate.query(SQL, new Object[] { id }, new PurchaseOrderMapper());
		return purchaseOrders;
	}

	@Override
	public List<PurchaseOrder> getPurchaseOrderByCustomerName(String name) {
		// TODO Auto-generated method stub
		String SQL = "select p.po_id as po_id,line_no,customer_name,po_date,prod_name,quantity,total_cost from inventorydb.purchase_order as p,inventorydb.order_detail as o where p.po_id=o.po_id and customer_name=? group by o.po_id,customer_name,po_date,total_cost,prod_name,quantity,line_no,p.po_id order by p.po_id";
		List<PurchaseOrder> purchaseOrders = jdbcTemplate.query(SQL, new Object[] { name }, new PurchaseOrderMapper());
		return purchaseOrders;
	}
}