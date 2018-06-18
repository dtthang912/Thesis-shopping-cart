package com.thang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.thang.entity.Employee;
import com.thang.form.LoginForm;
import com.thang.service.EmployeeService;

@Controller
@SessionAttributes("employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employee/login")
	public String displayEmployeeLoginPage(@ModelAttribute("loginForm") LoginForm loginForm) throws Exception {
		return "employeeLogin";
	}
	
	@GetMapping("/employee")
	public String displayEmployeeHomePage() throws Exception {
		return "employeeIndex";
	}
	
	@PostMapping("/employee/login")
	public String authenticateEmployee(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model)
			throws Exception {
		Employee employee = employeeService.loginByEmployeeAccount(loginForm.getUserName(), loginForm.getPass());
		if (employee == null) {
			bindingResult.reject("login.invalid");
			return "employeeLogin";
		}
		model.addAttribute("employee", employee);
		return "redirect:/employee";
	}
}
