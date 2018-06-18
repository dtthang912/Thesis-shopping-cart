package com.thang.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.thang.entity.Category;
import com.thang.entity.Customer;
import com.thang.entity.LineItem;
import com.thang.entity.Order;
import com.thang.entity.OrderStatus;
import com.thang.entity.PaymentType;
import com.thang.form.DeliveryForm;
import com.thang.form.LoginForm;
import com.thang.form.ProductSearchForm;
import com.thang.service.OrderService;
import com.thang.service.ProductRelatedService;

@Controller
@SessionAttributes({ "customer", "lineItemList" })
public class OrderController {

	@Autowired
	private ProductRelatedService productRelatedService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/checkout")
	public String displayCheckOutPage(@ModelAttribute("deliveryForm") DeliveryForm deliveryForm,
			@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, @ModelAttribute("customer") Customer customer,
			Model model) {
		List<Category> rootCategoryList = productRelatedService.getRootCategoryList();
		deliveryForm.setNum(customer.getNum());
		deliveryForm.setStreet(customer.getStreet());
		deliveryForm.setDistric(customer.getDistric());
		deliveryForm.setCity(customer.getCity());
		deliveryForm.setContactNumber(customer.getPhone());
		model.addAttribute("rootCategoryList", rootCategoryList);
		return "checkout";
	}

	@PostMapping("/checkout")
	public String createOrder(@ModelAttribute("customer") Customer customer,
			@ModelAttribute("deliveryForm") DeliveryForm deliveryForm,
			@ModelAttribute("lineItemList") List<LineItem> lineItemList, HttpServletRequest request, SessionStatus status) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setDate(new Date());
		order.setNum(deliveryForm.getNum());
		order.setStreet(deliveryForm.getStreet());
		order.setDistric(deliveryForm.getDistric());
		order.setCity(deliveryForm.getCity());
		order.setAdditionalAddressInfo(deliveryForm.getAdditionalAddressInfo());
		order.setContactNumber(deliveryForm.getContactNumber());
		order.setLineItems(lineItemList);
		order.setPaymentType(PaymentType.valueOf(deliveryForm.getPaymentType()));
		order.setStatus(OrderStatus.SUBMITTED);
		orderService.createOrder(order);
		
		return "redirect:/";
	}
}
