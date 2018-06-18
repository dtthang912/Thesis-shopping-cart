package com.thang.repository;

import java.util.List;

import com.thang.entity.Product;

public interface ProductDAO{
	
	void create(Product entity);

	void delete(Product entity);

	void update(Product entity);

	Product find(Integer id);

	void updateList(List<Product> entityList);

	void deleteList(List<Product> entityList);

	List<Product> getByIds(List<Integer> ids);

	List<Product> getInPagination(int offset, int limit);

	List<Product> getByCriteriaInPagination(String name, List<String> categoryTitles, String distributor,int offset, int limit);
	
	List<Product> getLatestProducts(int noOfDisplayLatestProduct);

	long countAll();

	long countByCriteria(String name, List<String> categoryTitles, String distributor);
}