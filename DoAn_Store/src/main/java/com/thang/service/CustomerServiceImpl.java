package com.thang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thang.config.GeneralPageConfig;
import com.thang.entity.Customer;
import com.thang.repository.CustomerDAO;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private PaginationService paginationService;

	@Override
	@Transactional(readOnly = true)
	public Customer loginByCustomerAccount(String userName, String pass) {
		return customerDAO.getByUserNameAndPass(userName, pass);
	}

	@Override
	@Transactional
	public void create(Customer customer) {
		customerDAO.create(customer);
	}

	@Override
	@Transactional
	public Customer getByUserName(String userName) {
		return customerDAO.getByUserName(userName);
	}
	
	@Override
	@Transactional
	public void edit(Customer customer) {
		customerDAO.update(customer);
	}
	
	@Override
	@Transactional(readOnly = true)
	public int getMaxPageIndex() { 
		return (int) customerDAO.countAll() / GeneralPageConfig.NO_OF_CUSTOMER_RECORDS_PER_PAGE + 1;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Integer> getPageIndexes(int currentPageIndex) {
		return paginationService.calculatePageIndexes(currentPageIndex, getMaxPageIndex());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> getCustomersForPagination(int currentPageIndex) {
		List<Customer> customerList = customerDAO.getAll(
				(currentPageIndex - 1) * GeneralPageConfig.NO_OF_CUSTOMER_RECORDS_PER_PAGE,
				GeneralPageConfig.NO_OF_CUSTOMER_RECORDS_PER_PAGE);
		return customerList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> getByIds(List<Integer> ids) {
		return customerDAO.getByIds(ids);
	}

	@Override
	@Transactional
	public void editList(List<Customer> customerList) {
		customerDAO.updateList(customerList);
	}

	@Override
	@Transactional
	public void deleteList(List<Customer> customerList) {
		customerDAO.deleteList(customerList);
		
	}
	
}
