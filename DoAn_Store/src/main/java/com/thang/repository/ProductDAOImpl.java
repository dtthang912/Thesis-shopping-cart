package com.thang.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import com.thang.entity.Product;

@Repository("productDAO")
public class ProductDAOImpl extends GenericDAO<Product> implements ProductDAO {

	public ProductDAOImpl() {
		super(Product.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getByIds(List<Integer> ids) {
		Session session = getSession();
		List<Product> productList = session.getNamedQuery("Product.getByIds").setParameter("ids", ids).getResultList();
		return productList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getInPagination(int offset, int limit) {
		Session session = getSession();
		List<Product> productList = session.getNamedQuery("Product.getAll").setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		return productList;
	}

	@Override
	public List<Product> getByCriteriaInPagination(String name, List<String> categoryTitles, String distributorName,
			int offset, int limit) {
		Session session = getSession();
		List<Product> productList = new ArrayList<>();
		NativeQuery<Product> nativeQuery = null;
		int indexSkip = 0;

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < categoryTitles.size(); i++) {
			builder.append("ct.title = ? OR ");
		}

		// remove distributor condition if distributor's name is empty
		if (distributorName.trim().equals("") || distributorName.trim().equalsIgnoreCase("All")) {
			indexSkip = 1;
			nativeQuery = session
					.createNativeQuery(
							"SELECT DISTINCT p.* FROM product p INNER JOIN category c ON p.idCategory = c.id "
									+ "INNER JOIN distributor d ON p.idDistributor = d.id "
									+ "INNER JOIN product_translation pt ON pt.idProduct = p.id "
									+ "INNER JOIN category_translation ct ON ct.idCategory = c.id "
									+ "WHERE pt.name like ? AND ("
									+ builder.delete(builder.length() - 4, builder.length()).toString() + ")",
							Product.class);
			nativeQuery.setParameter(1, "%" + name + "%").setFirstResult(offset).setMaxResults(limit);
		} else {
			indexSkip = 2;
			nativeQuery = session
					.createNativeQuery(
							"SELECT DISTINCT p.* FROM product p INNER JOIN category c ON p.idCategory = c.id "
									+ "INNER JOIN distributor d ON p.idDistributor = d.id "
									+ "INNER JOIN product_translation pt ON pt.idProduct = p.id "
									+ "INNER JOIN category_translation ct ON ct.idCategory = c.id "
									+ "WHERE (pt.name like ? AND d.name = ?) AND ("
									+ builder.delete(builder.length() - 4, builder.length()).toString() + ")",
							Product.class);
			nativeQuery.setParameter(1, "%" + name + "%").setParameter(2, distributorName).setFirstResult(offset)
					.setMaxResults(limit);
		}
		for (int i = 1; i <= categoryTitles.size(); i++) {
			nativeQuery.setParameter(i + indexSkip, categoryTitles.get(i - 1));
		}
		productList = nativeQuery.getResultList();
		return productList;
	}

	@Override
	public long countAll() {
		Session session = getSession();
		return (Long) session.getNamedQuery("Product.countAll").getSingleResult();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public long countByCriteria(String name, List<String> categoryTitles, String distributorName) {
		Session session = getSession();
		NativeQuery nativeQuery = null;
		int indexSkip = 0;

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < categoryTitles.size(); i++) {
			builder.append("ct.title = ? OR ");
		}

		// remove distributor condition if distributor's name is empty
		if (distributorName.trim().equals("") || distributorName.trim().equalsIgnoreCase("All")) {
			indexSkip = 1;
			nativeQuery = session.createNativeQuery(
					"SELECT COUNT(*) FROM (SELECT DISTINCT p.* FROM product p INNER JOIN category c ON p.idCategory = c.id "
							+ "INNER JOIN distributor d ON p.idDistributor = d.id "
							+ "INNER JOIN product_translation pt ON pt.idProduct = p.id "
							+ "INNER JOIN category_translation ct ON ct.idCategory = c.id "
							+ "WHERE pt.name like ? AND ("
							+ builder.delete(builder.length() - 4, builder.length()).toString() + ")) x");
			nativeQuery.setParameter(1, "%" + name + "%");
		} else {
			indexSkip = 2;
			nativeQuery = session.createNativeQuery(
					"SELECT COUNT(*) FROM (SELECT DISTINCT p.* FROM product p INNER JOIN category c ON p.idCategory = c.id "
							+ "INNER JOIN distributor d ON p.idDistributor = d.id "
							+ "INNER JOIN product_translation pt ON pt.idProduct = p.id "
							+ "INNER JOIN category_translation ct ON ct.idCategory = c.id "
							+ "WHERE (pt.name like ? AND d.name = ?) AND ("
							+ builder.delete(builder.length() - 4, builder.length()).toString() + ")) x");
			nativeQuery.setParameter(1, "%" + name + "%").setParameter(2, distributorName);
		}
		for (int i = 1; i <= categoryTitles.size(); i++) {
			nativeQuery.setParameter(i + indexSkip, categoryTitles.get(i - 1));
		}
		return ((BigInteger) nativeQuery.getSingleResult()).longValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getLatestProducts(int noOfDisplayLatestProduct) {
		Session session = getSession();
		List<Product> productList = session.getNamedQuery("Product.getLatestProducts")
				.setMaxResults(noOfDisplayLatestProduct).getResultList();
		return productList;
	}

}
