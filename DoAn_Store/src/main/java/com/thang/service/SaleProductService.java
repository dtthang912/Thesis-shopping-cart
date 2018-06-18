package com.thang.service;

import java.util.List;

import com.thang.entity.SaleProduct;

public interface SaleProductService {

	void create(SaleProduct saleProduct);

	void edit(SaleProduct saleProduct);

	int getMaxPageIndex();

	List<Integer> getPageIndexes(int currentPageIndex);

	List<SaleProduct> getSaleProductsForPagination(int currentPageIndex);

	List<SaleProduct> getByIds(List<Integer> ids);

	void editList(List<SaleProduct> saleProductList);

	void deleteList(List<SaleProduct> saleProductList);

	List<SaleProduct> getSaleProducts();
	
	List<SaleProduct> getLatestSaleProducts();
}