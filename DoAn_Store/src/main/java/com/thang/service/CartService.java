package com.thang.service;

import java.util.List;

import com.thang.entity.LineItem;

public interface CartService {

	void addProductToCart(int productId,int quantity);

	List<LineItem> getLineItemList();
	
	void editCart(int productId, int quantity);

	void removeProductInCart(int index);

	void removeAll();

}