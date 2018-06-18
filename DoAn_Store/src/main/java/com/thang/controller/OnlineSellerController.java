package com.thang.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.thang.entity.Order;
import com.thang.form.OrderForm;
import com.thang.service.OrderService;

@Controller
public class OnlineSellerController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("employee/manageOrder")
	public String displayOrders(@ModelAttribute("orderForm") OrderForm orderForm, Model model,
			HttpServletResponse response) throws Exception {
		List<Order> orderList = new ArrayList<>();
		List<Integer> pageIndexes = new ArrayList<>();
		try {
			if ((orderForm.getPage() < 0) || (orderForm.getPage() > orderService.getMaxPageIndex())) {
				throw new NumberFormatException();
			}

			// Calculate number of pages that will be displayed
			pageIndexes = orderService.getPageIndexes(orderForm.getPage());
			orderList = orderService.getOrdersForPagination(orderForm.getPage());

			model.addAttribute("pageIndexes", pageIndexes);
			model.addAttribute("orderList", orderList);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return "manageOrder";
	}

	@PostMapping("employee/manageOrder")
	public String changeOrderStatus(@ModelAttribute("orderForm") OrderForm orderForm) {
		orderService.setStatus(orderForm.getOrderId(),orderForm.getStatus());
		return "redirect:/employee/manageOrder?page=" + orderForm.getPage();

	}
}
