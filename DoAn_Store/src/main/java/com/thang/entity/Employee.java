package com.thang.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@NamedQueries({
		@NamedQuery(name = "Employee.getByUserNameAndPass", query = "SELECT e FROM Employee e WHERE e.userName = :userName AND e.pass = :pass AND e.deleted is false") })
public class Employee extends User {

	private static final long serialVersionUID = 1951437984023136053L;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "employee_role", joinColumns = @JoinColumn(name = "idEmployee", referencedColumnName = "id"))
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private List<Role> roleList;

	@Column(name = "salary")
	private float salary;

	public Employee() {
		
	}

	public Employee(String userName, String pass, String fName, String mName, String lName, String num, String street,
			String distric, String city, int phone, String email, Date dateCreated, String gender, List<Role> roleList,
			float salary) {
		super(userName, pass, fName, mName, lName, num, street, distric, city, phone, email, dateCreated, gender);
		this.roleList = roleList;
		this.salary = salary;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public float getSalary() {
		return salary;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
	//
}
