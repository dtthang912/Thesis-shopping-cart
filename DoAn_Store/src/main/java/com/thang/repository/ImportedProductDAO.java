package com.thang.repository;

import java.util.List;

import com.thang.entity.ImportedProduct;

public interface ImportedProductDAO {

	List<ImportedProduct> getByDay();

	List<ImportedProduct> getByMonth();

	List<ImportedProduct> getByYear();

}