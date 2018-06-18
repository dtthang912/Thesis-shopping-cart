package com.thang.form;

public class OrderForm {
	private String status;
	private int page = 1;
	private int orderId;
	
	public OrderForm() {
		
	}
	public String getStatus() {
		return status;
	}
	public int getPage() {
		return page;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
}
