package com.thang.form;

import java.util.List;

import com.thang.entity.Customer;

public class CustomerForm {
	private int cPage = 1;
	
	//customer's properties
	private String userName;
	private String pass;
	private String fName;
	private String mName;
	private String lName;
	private String num;
	private String street;
	private String distric;
	private String city;
	private int phone;
	private String email;
	private String gender;

	private List<String> selectedIds;
	
	// List of students for editing
	private List<Customer> customerList;

	// Method for processing
	private String method;
	
	public CustomerForm() {

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getcPage() {
		return cPage;
	}

	public List<String> getSelectedIds() {
		return selectedIds;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public String getMethod() {
		return method;
	}

	public void setcPage(int cPage) {
		this.cPage = cPage;
	}

	public void setSelectedIds(List<String> selectedIds) {
		this.selectedIds = selectedIds;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
