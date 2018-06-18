package com.thang.service;

import com.thang.entity.Employee;

public interface EmployeeService {

	Employee loginByEmployeeAccount(String userName, String pass);

}