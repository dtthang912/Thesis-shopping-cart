package com.thang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thang.entity.Employee;
import com.thang.repository.EmployeeDAO;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Override
	@Transactional(readOnly = true)
	public Employee loginByEmployeeAccount(String userName, String pass) {
		return employeeDAO.getByUserNameAndPass(userName, pass);
	}
	
}
