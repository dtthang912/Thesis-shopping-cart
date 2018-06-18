package com.thang.service;

import java.util.List;

import com.thang.entity.Order;

public interface OrderService {

	void createOrder(Order order);

	void setStatus(int orderId, String orderStatus);
	
	List<Integer> getPageIndexes(int currentPageIndex);

	List<Order> getOrdersForPagination(int currentPageIndex);
	
	int getMaxPageIndex();
	
}