package com.thang.repository;

import java.util.List;

import com.thang.entity.Order;

public interface OrderDAO {
	void create(Order entity);

	void delete(Order entity);

	void update(Order entity);

	Order find(Integer id);

	void updateList(List<Order> entityList);

	void deleteList(List<Order> entityList);
	
	List<Order> getAll(int offset, int limit);
	
	long countAll();
	
	List<Order> getByDay();

	List<Order> getByMonth();

	List<Order> getByYear();
}