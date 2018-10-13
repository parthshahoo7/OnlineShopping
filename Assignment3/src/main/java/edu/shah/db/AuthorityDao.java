package edu.shah.db;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDao {
	public List<String> getAllRolesByEmail(String Email);
}
