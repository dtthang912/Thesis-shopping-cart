package com.thang.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.thang.entity.Category;
import com.thang.entity.Customer;
import com.thang.entity.CustomerType;
import com.thang.form.CustomerForm;
import com.thang.form.LoginForm;
import com.thang.form.ProductSearchForm;
import com.thang.service.CustomerService;
import com.thang.service.ProductRelatedService;

@Controller
@SessionAttributes("customer")
public class CustomerController {
	@Autowired
	ProductRelatedService productRelatedService;

	@Autowired
	CustomerService customerService;

	@GetMapping("/login")
	public String displayCustomerLoginPage(@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, Model model) throws Exception {
		List<Category> rootCategoryList = productRelatedService.getRootCategoryList();
		model.addAttribute("rootCategoryList", rootCategoryList);
		return "login";
	}

	@GetMapping("/register")
	public String displayCustomerRegisterPage(@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, @ModelAttribute("customerForm") CustomerForm customerForm,
			Model model) throws Exception {
		List<Category> rootCategoryList = productRelatedService.getRootCategoryList();
		model.addAttribute("rootCategoryList", rootCategoryList);
		return "register";
	}

	@GetMapping("/profile")
	public String displayCustomerProfilePage(@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, @ModelAttribute("customerForm") CustomerForm customerForm,
			@ModelAttribute("customer") Customer customer, Model model) throws Exception {
		List<Category> rootCategoryList = productRelatedService.getRootCategoryList();
		model.addAttribute("rootCategoryList", rootCategoryList);
		customerForm.setEmail(customer.getEmail());
		customerForm.setfName(customer.getfName());
		customerForm.setmName(customer.getmName());
		customerForm.setlName(customer.getlName());
		customerForm.setNum(customer.getNum());
		customerForm.setStreet(customer.getStreet());
		customerForm.setDistric(customer.getDistric());
		customerForm.setCity(customer.getCity());
		customerForm.setPhone(customer.getPhone());
		return "profile";
	}

	@PostMapping("/profile")
	public String editCustomerProfile(@ModelAttribute("customerForm") CustomerForm customerForm,@ModelAttribute("customer") Customer customerInfo,  Model model) throws Exception {
		Customer customer = new Customer();
		customer.setId(customerInfo.getId());
		customer.setUserName(customerInfo.getUserName());
		customer.setPass(customerInfo.getPass());
		customer.setEmail(customerForm.getEmail());
		customer.setfName(customerForm.getfName());
		customer.setmName(customerForm.getmName());
		customer.setlName(customerForm.getlName());
		customer.setNum(customerForm.getNum());
		customer.setStreet(customerForm.getStreet());
		customer.setDistric(customerForm.getDistric());
		customer.setCity(customerForm.getCity());
		customer.setPhone(customerForm.getPhone());
		customer.setType(customerInfo.getType());
		customerService.edit(customer);
		return "redirect:/profile";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("customerForm") CustomerForm customerForm, BindingResult bindingResult,@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, Model model) throws Exception {
		if(customerService.getByUserName(customerForm.getUserName()) != null){
			bindingResult.reject("account.existed");
			return "register";
		} 
		Customer customer = new Customer();
		customer.setUserName(customerForm.getUserName());
		customer.setPass(customerForm.getPass());
		customer.setEmail(customerForm.getEmail());
		customer.setfName(customerForm.getfName());
		customer.setmName(customerForm.getmName());
		customer.setlName(customerForm.getlName());
		customer.setNum(customerForm.getNum());
		customer.setStreet(customerForm.getStreet());
		customer.setDistric(customerForm.getDistric());
		customer.setCity(customerForm.getCity());
		customer.setPhone(customerForm.getPhone());
		customer.setGender(customerForm.getGender());
		customer.setDateCreated(new Date());
		customer.setType(CustomerType.NORMAL);
		customerService.create(customer);
		return "redirect:/login";
	}

	@PostMapping("/login")
	public String authenticateCustomer(@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model)
			throws Exception {
		Customer customer = customerService.loginByCustomerAccount(loginForm.getUserName(), loginForm.getPass());
		if (customer == null) {
			bindingResult.reject("login.invalid");
			return "login";
		}
		model.addAttribute("customer", customer);
		return "redirect:/";
	}
}
