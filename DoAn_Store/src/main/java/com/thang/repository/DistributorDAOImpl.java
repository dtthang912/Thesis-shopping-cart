package com.thang.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import com.thang.entity.Distributor;

@Repository("distributorDAO")
public class DistributorDAOImpl extends GenericDAO<Distributor> implements DistributorDAO {

	public DistributorDAOImpl() {
		super(Distributor.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Distributor> getAll() {
		Session session = getSession();
		List<Distributor> distributorList = session.getNamedQuery("Distributor.getAll").getResultList();
		return distributorList;
	}

	@Override
	public List<Distributor> getByCriteria(String name, List<String> categoryTitles) {
		Session session = getSession();
		List<Distributor> distributorList = new ArrayList<>();
		NativeQuery<Distributor> nativeQuery = null;

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < categoryTitles.size(); i++) {
			builder.append("ct.title = ? OR ");
		}

		nativeQuery = session
				.createNativeQuery("SELECT DISTINCT d.* FROM product p INNER JOIN category c ON p.idCategory = c.id "
						+ "INNER JOIN distributor d ON p.idDistributor = d.id  "
						+ "INNER JOIN product_translation pt ON pt.idProduct = p.id "
						+ "INNER JOIN category_translation ct ON ct.idCategory = c.id " + "WHERE pt.name like ? AND ("
						+ builder.delete(builder.length() - 4, builder.length()).toString() + ")", Distributor.class);
		nativeQuery.setParameter(1, "%" + name + "%");
		for (int i = 1; i <= categoryTitles.size(); i++) {
			nativeQuery.setParameter(i + 1, categoryTitles.get(i - 1));
		}
		
		distributorList = nativeQuery.getResultList();
		return distributorList;
	}
}
