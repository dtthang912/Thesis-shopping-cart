package com.thang.repository;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thang.entity.Order;

@Repository("orderDAO")
public class OrderDAOImpl extends GenericDAO<Order> implements OrderDAO {
	public OrderDAOImpl() {
		super(Order.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getAll(int offset, int limit) {
		Session session = getSession();
		List<Order> orderList = session.getNamedQuery("Order.getAll").setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		return orderList;
	}

	@Override
	public long countAll() {
		Session session = getSession();
		try {
			return (Long) session.getNamedQuery("Order.countAll").getSingleResult();
		} catch (NoResultException e) {

		}
		return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getByDay() {
		Session session = getSession();
		List<Order> orderList = session.getNamedQuery("Order.getByDay").getResultList();
		return orderList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getByMonth() {
		Session session = getSession();
		List<Order> orderList = session.getNamedQuery("Order.getByMonth").getResultList();
		return orderList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getByYear() {
		Session session = getSession();
		List<Order> orderList = session.getNamedQuery("Order.getByYear").getResultList();
		return orderList;
	}
}
