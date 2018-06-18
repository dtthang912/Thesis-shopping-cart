package com.thang.form;

public class DeliveryForm {
	private String num = "";
	private String street = "";
	private String distric = "";
	private String city = "";
	private String additionalAddressInfo = "";
	private int contactNumber;
	private String paymentType = "";

	public DeliveryForm() {

	}

	public DeliveryForm(String num, String street, String distric, String city, int contactNumber, String additionalAddressInfo, String paymentType) {
		this.num = num;
		this.street = street;
		this.distric = distric;
		this.city = city;
		this.contactNumber = contactNumber;
		this.additionalAddressInfo = additionalAddressInfo;
		this.paymentType = paymentType;
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

	public int getContactNumber() {
		return contactNumber;
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

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAdditionalAddressInfo() {
		return additionalAddressInfo;
	}

	public void setAdditionalAddressInfo(String additionalAddressInfo) {
		this.additionalAddressInfo = additionalAddressInfo;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

}
