package edu.shah.db;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.shah.model.Product;

@Repository
public interface ProductDao {
	public Product createProduct(Product product);

	public int deleteProduct(Product product);

	public Product getProductById(int id);

	public List<Product> getAllProducts();

	public int update(Product product);

}
