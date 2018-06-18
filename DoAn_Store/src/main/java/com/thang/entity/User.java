package com.thang.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

	private static final long serialVersionUID = 4110604698810189659L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;

	@Column(name = "userName")
	protected String userName;

	@Column(name = "pass")
	protected String pass;

	@Column(name = "fName")
	protected String fName;

	@Column(name = "mName")
	protected String mName;

	@Column(name = "lName")
	protected String lName;

	@Column(name = "num")
	protected String num;

	@Column(name = "street")
	protected String street;

	@Column(name = "distric")
	protected String distric;

	@Column(name = "city")
	protected String city;

	@Column(name = "phone")
	protected int phone;

	@Column(name = "email")
	protected String email;

	@Column(name = "dateCreated")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	protected Date dateCreated;

	@Column(name = "gender")
	protected String gender;

	@Column(name = "deleted")
	@Type(type = "numeric_boolean")
	protected boolean deleted;

	public User() {

	}

	public User(String userName, String pass, String fName, String mName, String lName, String num, String street,
			String distric, String city, int phone, String email, Date dateCreated, String gender) {
		this.userName = userName;
		this.pass = pass;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.num = num;
		this.street = street;
		this.distric = distric;
		this.city = city;
		this.phone = phone;
		this.email = email;
		this.dateCreated = dateCreated;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPass() {
		return pass;
	}

	public String getfName() {
		return fName;
	}

	public String getmName() {
		return mName;
	}

	public String getlName() {
		return lName;
	}

	public String getNum() {
		return num;
	}

	public String getStreet() {
		return street;
	}

	public String getDistric() {
		return distric;
	}

	public String getCity() {
		return city;
	}

	public int getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setDistric(String distric) {
		this.distric = distric;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public String getGender() {
		return gender;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
