package com.thang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thang.config.GeneralPageConfig;
import com.thang.entity.LineItem;
import com.thang.entity.Order;
import com.thang.entity.OrderStatus;
import com.thang.repository.OrderDAO;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private PaginationService paginationService;

	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private CartService cartService;
	
	@Override
	@Transactional
	public void createOrder(Order order) {
		for(LineItem lineItem : order.getLineItems()){
			lineItem.getProduct().setQuantity(lineItem.getProduct().getQuantity() - lineItem.getQuantity());
		}
		orderDAO.create(order);
		cartService.removeAll();
	}
	
	@Override
	@Transactional
	public void setStatus(int orderId, String orderStatus) {
		Order order = orderDAO.find(orderId);
		order.setStatus(OrderStatus.valueOf(orderStatus));
		orderDAO.update(order);
	}
	
	@Override
	@Transactional(readOnly = true)
	public int getMaxPageIndex() {
		return (int) orderDAO.countAll() / GeneralPageConfig.NO_OF_ORDER_RECORDS_PER_PAGE + 1;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Integer> getPageIndexes(int currentPageIndex) {
		return paginationService.calculatePageIndexes(currentPageIndex, getMaxPageIndex());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> getOrdersForPagination(int currentPageIndex) {
		List<Order> orderList = orderDAO.getAll(
				(currentPageIndex - 1) * GeneralPageConfig.NO_OF_ORDER_RECORDS_PER_PAGE,
				GeneralPageConfig.NO_OF_ORDER_RECORDS_PER_PAGE);
		return orderList;
	}
}
