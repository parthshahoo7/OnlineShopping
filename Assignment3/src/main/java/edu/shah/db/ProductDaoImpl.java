package edu.shah.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import edu.shah.model.Product;
import edu.shah.web.ProductMapper;

@Repository
public class ProductDaoImpl implements ProductDao {

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
	public Product createProduct(final Product product) {
		// TODO Auto-generated method stub
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {

			String SQL = "insert into inventorydb.product(id,productname,description,category,cost,discount)"
					+ "values (?, ? , ? , ? , ? , ?)";
			int category = 0;
			if (product.getCategory().toString().equalsIgnoreCase("CLOTH"))
				category = 1;
			if (product.getCategory().toString().equalsIgnoreCase("ELECTRONIC"))
				category = 2;
			if (product.getCategory().toString().equalsIgnoreCase("BOOK"))
				category = 3;

			jdbcTemplate.update(SQL, product.getId(), product.getName(), product.getDescription(), category,
					product.getCost(), product.getDiscount());
			String SQL1 = "insert into inventorydb.multilocation(prod_id,location_id) values(?,?)";
			for (int i = 0; i < product.getLocation().size(); i++) {
				int location = 0;

				if (product.getLocation().get(i).toString().equalsIgnoreCase("wareHouse1"))
					location = 1;
				if (product.getLocation().get(i).toString().equalsIgnoreCase("wareHouse3"))
					location = 3;
				if (product.getLocation().get(i).toString().equalsIgnoreCase("wareHouse2"))
					location = 2;
				jdbcTemplate.update(SQL1, product.getId(), location);

			}
			transactionManager.commit(status);
		} catch (DataAccessException e) {
			System.out.println("Error in creating product record, rolling back");
			transactionManager.rollback(status);
			throw e;
		}

		return product;
	}

	@Override
	public int deleteProduct(Product product) {
		// TODO Auto-generated method stub
		String SQL = "delete from inventorydb.product where id=?";
		jdbcTemplate.update(SQL, product.getId());
		return 1;
	}

	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		try {
			String SQL = " select p.id as p_id , p.productname as p_name,p.description as p_desc, p.cost as p_cost, p.discount as p_discount, string_agg(l.location,',') as l_location, c.name as c_name from inventorydb.warehouse as l,inventorydb.multilocation as m,inventorydb.product as p, inventorydb.category as c where p.id=m.prod_id and m.location_id=l.id and p.category=c.id and p.id= ? group by m.prod_id,p.id,c.name";
			Product product = jdbcTemplate.queryForObject(SQL, new Object[] { id }, new ProductMapper());
			return product;
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		String SQL = " select p.id as p_id , p.productname as p_name,p.description as p_desc, p.cost as p_cost, p.discount as p_discount, string_agg(l.location,',') as l_location, c.name as c_name from inventorydb.warehouse as l,inventorydb.multilocation as m,inventorydb.product as p, inventorydb.category as c where p.id=m.prod_id and m.location_id=l.id and p.category=c.id group by m.prod_id,p.id,c.name";
		List<Product> products = jdbcTemplate.query(SQL, new ProductMapper());
		return products;
	}

	@Override
	public int update(Product product) {
		// TODO Auto-generated method stub
		int category = 0;
		int location = 0;
		if (product.getCategory().toString().equalsIgnoreCase("CLOTH"))
			category = 1;
		if (product.getCategory().toString().equalsIgnoreCase("ELECTRONIC"))
			category = 2;
		if (product.getCategory().toString().equalsIgnoreCase("BOOK"))
			category = 3;
		if (product.getLocation().toString().equalsIgnoreCase("wareHouse1"))
			location = 1;
		if (product.getLocation().toString().equalsIgnoreCase("wareHouse2"))
			location = 2;
		if (product.getLocation().toString().equalsIgnoreCase("wareHouse3"))
			location = 3;
		String SQL = "update inventorydb.product set (name,description,category,cost,location,discount) = (?, ? , ? , ? , ? ,?) where id= ?";
		jdbcTemplate.update(SQL, product.getName(), product.getDescription(), category, product.getCost(), location,
				product.getDiscount(), product.getId());
		System.out.println("Updated Record with ID=" + product.getId());
		return 1;
	}

}