package com.thang.repository;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thang.entity.Employee;

@Repository("employeeDAO")
public class EmployeeDAOImpl extends GenericDAO<Employee> implements EmployeeDAO{
	public EmployeeDAOImpl() {
		super(Employee.class);
	}

	@Override
	public Employee getByUserNameAndPass(String userName, String pass) {
		Session session = getSession();
		try {
			Employee employee = (Employee) session.getNamedQuery("Employee.getByUserNameAndPass")
					.setParameter("userName", userName).setParameter("pass", pass).getSingleResult();
			return employee;
		} catch (NoResultException e) {

		}
		return null;
	}
}
