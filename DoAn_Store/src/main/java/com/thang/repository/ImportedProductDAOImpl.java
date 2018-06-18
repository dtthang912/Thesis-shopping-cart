package com.thang.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thang.entity.ImportedProduct;

@Repository("importedProductDAO")
public class ImportedProductDAOImpl extends GenericDAO<ImportedProduct> implements ImportedProductDAO {

	public ImportedProductDAOImpl() {
		super(ImportedProduct.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImportedProduct> getByDay() {
		Session session = getSession();
		List<ImportedProduct> importedProductList = session.getNamedQuery("ImportedProduct.getByDay").getResultList();
		return importedProductList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImportedProduct> getByMonth() {
		Session session = getSession();
		List<ImportedProduct> importedProductList = session.getNamedQuery("ImportedProduct.getByMonth").getResultList();
		return importedProductList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImportedProduct> getByYear() {
		Session session = getSession();
		List<ImportedProduct> importedProductList = session.getNamedQuery("ImportedProduct.getByYear").getResultList();
		return importedProductList;
	}

}
