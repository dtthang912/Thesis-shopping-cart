package com.thang.repository;

import com.thang.entity.Employee;

public interface EmployeeDAO {

	Employee getByUserNameAndPass(String userName, String pass);

}