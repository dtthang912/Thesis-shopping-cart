package com.thang.dto;

public class SoldProductInfo {
	private int productId;
	private String name;
	private float income;
	private int quantity;
	public SoldProductInfo() {
		
	}
	public int getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}
	public float getIncome() {
		return income;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIncome(float income) {
		this.income = income;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
