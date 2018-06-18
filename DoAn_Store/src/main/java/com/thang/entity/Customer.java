package com.thang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@NamedQueries({ @NamedQuery(name = "Customer.countAll", query = "SELECT COUNT(*) FROM Customer c WHERE c.deleted is false"),
		@NamedQuery(name = "Customer.getAll", query = "SELECT c FROM Customer c WHERE c.deleted is false"),
		@NamedQuery(name = "Customer.getByIds", query = "SELECT c FROM Customer c WHERE c.id IN :ids AND c.deleted is false"),
		@NamedQuery(name = "Customer.getByUserNameAndPass", query = "SELECT c FROM Customer c WHERE c.userName = :userName AND c.pass = :pass AND c.deleted is false"),
		@NamedQuery(name = "Customer.getByUserName", query = "SELECT c FROM Customer c WHERE c.userName = :userName")})
public class Customer extends User {

	private static final long serialVersionUID = 812132281423172635L;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private CustomerType type;

	public Customer() {

	}

	public Customer(String userName, String pass, String fName, String mName, String lName, String num, String street,
			String distric, String city, int phone, String email, Date dateCreated, String gender, CustomerType type) {
		super(userName, pass, fName, mName, lName, num, street, distric, city, phone, email, dateCreated, gender);
		this.type = type;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

}
