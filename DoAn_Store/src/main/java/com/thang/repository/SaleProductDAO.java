package com.thang.repository;

import java.util.List;

import com.thang.entity.SaleProduct;

public interface SaleProductDAO {

	void create(SaleProduct saleProduct);

	void delete(SaleProduct saleProduct);

	void update(SaleProduct saleProduct);

	SaleProduct find(Integer id);

	void updateList(List<SaleProduct> saleProductList);

	List<SaleProduct> getLatestSaleProducts(int noOfDisplaySaleProduct);

	List<SaleProduct> getSaleProducts();

	List<SaleProduct> getAll(int offset, int limit);

	long countAll();

	List<SaleProduct> getByIds(List<Integer> ids);

	void deleteList(List<SaleProduct> saleProductList);
}