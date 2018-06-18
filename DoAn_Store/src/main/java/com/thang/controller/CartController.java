package com.thang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.thang.entity.Category;
import com.thang.entity.LineItem;
import com.thang.form.CartForm;
import com.thang.form.LoginForm;
import com.thang.form.ProductSearchForm;
import com.thang.service.CartService;
import com.thang.service.ProductRelatedService;

@Controller
@SessionAttributes("lineItemList")
public class CartController {

	@Autowired
	private ProductRelatedService productRelatedService;

	@Autowired
	private CartService cartService;

	@GetMapping(path = "/addToCart", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LineItem> addToCart(@ModelAttribute("cartForm") CartForm cartForm, Model model) throws Exception {
		cartService.addProductToCart(cartForm.getProductId(), cartForm.getQuantity());
		model.addAttribute("lineItemList", cartService.getLineItemList());
		return cartService.getLineItemList();
	}

	@PostMapping("/cart")
	public String editCart(@ModelAttribute("cartForm") CartForm cartForm,@ModelAttribute("loginForm") LoginForm loginForm,
			@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm, Model model) throws Exception {
		List<Category> rootCategoryList = productRelatedService.getRootCategoryList();
		if (cartForm.getMethod().equals("increase")) {
			cartService.editCart(cartForm.getProductId(), cartForm.getQuantity() + 1);
		} else if (cartForm.getMethod().equals("decrease")) {
			cartService.editCart(cartForm.getProductId(), cartForm.getQuantity() - 1);
		} else if (cartForm.getMethod().equals("delete")) {
			cartService.removeProductInCart(cartForm.getProductId());
		}
		model.addAttribute("lineItemList", cartService.getLineItemList());
		model.addAttribute("rootCategoryList", rootCategoryList);
		return "cart";
	}

	@GetMapping("/cart")
	public String getCart(@ModelAttribute("cartForm") CartForm cartForm,
			@ModelAttribute("loginForm") LoginForm loginForm,
			@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm, Model model) throws Exception {
		List<Category> rootCategoryList = productRelatedService.getRootCategoryList();
		model.addAttribute("lineItemList", cartService.getLineItemList());
		model.addAttribute("rootCategoryList", rootCategoryList);
		return "cart";
	}
}
