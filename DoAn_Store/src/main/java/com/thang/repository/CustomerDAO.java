package com.thang.repository;

import java.util.List;

import com.thang.entity.Customer;

public interface CustomerDAO {

	void create(Customer entity);

	void delete(Customer entity);

	void update(Customer entity);

	Customer find(Integer id);

	void updateList(List<Customer> entityList);

	void deleteList(List<Customer> entityList);

	Customer getByUserNameAndPass(String userName, String pass);

	List<Customer> getAll(int offset, int limit);

	long countAll();

	List<Customer> getByIds(List<Integer> ids);
	
	Customer getByUserName(String userName);
}