package com.library.dao;

import java.util.List;

public interface Dao<T> {
	
	long save(T entity);
	
	void update(T entity);
	
	boolean delete(Long entity);
	
	T getDetails(long id);
	
	List<T> fetchAll();
}
