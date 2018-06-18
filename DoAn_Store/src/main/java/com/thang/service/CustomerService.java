package com.thang.service;

import java.util.List;

import com.thang.entity.Customer;

public interface CustomerService {
	void edit(Customer customer);

	void create(Customer customer);

	Customer loginByCustomerAccount(String userName, String pass);
	
	Customer getByUserName(String userName);

	int getMaxPageIndex();

	List<Integer> getPageIndexes(int currentPageIndex);

	List<Customer> getCustomersForPagination(int currentPageIndex);

	List<Customer> getByIds(List<Integer> ids);

	void editList(List<Customer> customerList);

	void deleteList(List<Customer> customerList);

}