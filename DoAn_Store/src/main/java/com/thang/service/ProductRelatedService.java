package com.thang.service;

import java.util.List;

import com.thang.entity.Category;
import com.thang.entity.Product;

public interface ProductRelatedService {

	List<Product> getLatestProducts();

	List<Category> getRootCategoryList();
	
	Product getProductById(int id);
	
	int getMaxPageIndex(String name, String category, String distributor);
	
	List<Product> getProductsForPagination(String name, String category, String distributor, int currentPageIndex);
	
	List<Integer> getPageIndexes(String name, String categoryTitle, String distributor, int currentPageIndex);

	List<String> getLeafCategoryTitles(String categoryTitle);

	List<String> getAllLeafCategoryTitles();
	
}