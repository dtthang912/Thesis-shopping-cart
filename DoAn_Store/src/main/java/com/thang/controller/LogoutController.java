package com.thang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
	@GetMapping("/logout")
	  public String logout(HttpServletRequest request) throws Exception{
		  request.getSession().invalidate();
		  return "redirect:/";
	  }
	@GetMapping("/employee/logout")
	  public String employeeLogout(HttpServletRequest request) throws Exception{
		  request.getSession().invalidate();
		  return "redirect:/employee";
	  }
}
