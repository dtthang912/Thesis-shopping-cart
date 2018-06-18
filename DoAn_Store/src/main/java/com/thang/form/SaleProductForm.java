package com.thang.form;

import java.util.List;

import com.thang.entity.SaleProduct;

public class SaleProductForm {
	private int sPage = 1;
	
	//Sale product's properties
	private int productId;
	private int eventId;
	private float salePrice;

	private List<String> selectedIds;
	
	// List of sale products for editing
	private List<SaleProduct> saleProductList;

	// Method for processing
	private String method;

	public SaleProductForm() {
		
	}

	public int getsPage() {
		return sPage;
	}

	public int getProductId() {
		return productId;
	}

	public int getEventId() {
		return eventId;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public List<String> getSelectedIds() {
		return selectedIds;
	}

	public List<SaleProduct> getSaleProductList() {
		return saleProductList;
	}

	public String getMethod() {
		return method;
	}

	public void setsPage(int sPage) {
		this.sPage = sPage;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public void setSelectedIds(List<String> selectedIds) {
		this.selectedIds = selectedIds;
	}

	public void setSaleProductList(List<SaleProduct> saleProductList) {
		this.saleProductList = saleProductList;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	
}
