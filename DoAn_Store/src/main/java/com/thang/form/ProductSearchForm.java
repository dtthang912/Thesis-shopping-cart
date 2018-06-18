package com.thang.form;

public class ProductSearchForm {
	private int productId;
	private String name = "";
	private String category = "";
	private String distributor = "";
	private int page = 1;
	
	public ProductSearchForm() {
	}
	
	public String getName() {
		return name;
	}
	public int getPage() {
		return page;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getCategory() {
		return category;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}
