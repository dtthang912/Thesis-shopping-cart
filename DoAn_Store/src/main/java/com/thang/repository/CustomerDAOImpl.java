package com.thang.repository;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thang.entity.Customer;

@Repository("customerDAO")
public class CustomerDAOImpl extends GenericDAO<Customer> implements CustomerDAO {

	public CustomerDAOImpl() {
		super(Customer.class);
	}

	@Override
	public Customer getByUserNameAndPass(String userName, String pass) {
		Session session = getSession();
		try {
			Customer customer = (Customer) session.getNamedQuery("Customer.getByUserNameAndPass")
					.setParameter("userName", userName).setParameter("pass", pass).getSingleResult();
			return customer;
		} catch (NoResultException e) {

		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> getAll(int offset, int limit) {
		Session session = getSession();
		List<Customer> customerList = session.getNamedQuery("Customer.getAll").setFirstResult(offset)
				.setMaxResults(limit).getResultList();
		return customerList;
	}

	@Override
	public long countAll() {
		Session session = getSession();
		try {
			return (Long) session.getNamedQuery("Customer.countAll").getSingleResult();
		} catch (NoResultException e) {

		}
		return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> getByIds(List<Integer> ids) {
		Session session = getSession();
		List<Customer> customerList = (List<Customer>) session.getNamedQuery("Customer.getByIds")
				.setParameter("ids", ids).getResultList();
		return customerList;
	}

	@Override
	public void deleteList(List<Customer> customerList) {
		Session session = getSession();
		for (Customer customer : customerList) {
			customer.setDeleted(true);
			session.update(customer);
		}
	}

	@Override
	public Customer getByUserName(String userName) {
		Session session = getSession();
		try {
			Customer customer = (Customer) session.getNamedQuery("Customer.getByUserName")
					.setParameter("userName", userName).getSingleResult();
			return customer;
		} catch (NoResultException e) {

		}
		return null;
	}
}
