package com.thang.repository;

import java.util.List;

import com.thang.entity.Category;

public interface CategoryDAO {

	List<Category> getRootList();
	
	Category getByName(String name);
	
	List<Category> getAll();
}