package edu.shah.db;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface LocationDao {

	public List<String> getAllLocation();
}