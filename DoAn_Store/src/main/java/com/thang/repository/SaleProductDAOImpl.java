package com.thang.repository;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thang.entity.SaleProduct;

@Repository("saleProductDAO")
public class SaleProductDAOImpl extends GenericDAO<SaleProduct> implements SaleProductDAO {

	public SaleProductDAOImpl() {
		super(SaleProduct.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SaleProduct> getLatestSaleProducts(int noOfDisplaySaleProduct) {
		Session session = getSession();
		List<SaleProduct> saleProductList = session.getNamedQuery("SaleProduct.getValidList")
				.setMaxResults(noOfDisplaySaleProduct).getResultList();
		return saleProductList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SaleProduct> getSaleProducts() {
		Session session = getSession();
		List<SaleProduct> saleProductList = session.getNamedQuery("SaleProduct.getValidList").getResultList();
		return saleProductList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SaleProduct> getAll(int offset, int limit) {
		Session session = getSession();
		List<SaleProduct> saleProductList = session.getNamedQuery("SaleProduct.getAll").setFirstResult(offset)
				.setMaxResults(limit).getResultList();
		return saleProductList;
	}

	@Override
	public long countAll() {
		Session session = getSession();
		try {
			return (Long) session.getNamedQuery("SaleProduct.countAll").getSingleResult();
		} catch (NoResultException e) {

		}
		return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SaleProduct> getByIds(List<Integer> ids) {
		Session session = getSession();
		List<SaleProduct> saleProductList = (List<SaleProduct>) session.getNamedQuery("SaleProduct.getByIds")
				.setParameter("ids", ids).getResultList();
		return saleProductList;
	}

	@Override
	public void deleteList(List<SaleProduct> saleProductList) {
		Session session = getSession();
		for (SaleProduct saleProduct : saleProductList) {
			saleProduct.setDeleted(true);
			session.update(saleProduct);
		}
	}
}
