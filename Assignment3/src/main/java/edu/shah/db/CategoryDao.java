package edu.shah.db;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao {

	public List<String> getAllCategory();
}
