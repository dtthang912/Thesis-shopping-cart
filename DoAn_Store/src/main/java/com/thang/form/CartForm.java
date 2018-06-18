package com.thang.form;

public class CartForm {
	private int productId;
	private int quantity = 1;
	private String method;

	public CartForm() {

	}

	public CartForm(int productId, int quantity, String method) {
		this.productId = productId;
		this.quantity = quantity;
		this.method = method;
	}

	public int getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getMethod() {
		return method;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
