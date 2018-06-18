package com.thang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException { 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		boolean isLoggedIn = (session != null) && (session.getAttribute("customer") != null);
		boolean isLogInRequest = (req.getRequestURI().equals(req.getContextPath() + "/login"));
		boolean isRegisterRequest = (req.getRequestURI().equals(req.getContextPath() + "/register"));

		if ((!isLoggedIn && (isLogInRequest || isRegisterRequest))
				|| (isLoggedIn && !isLogInRequest && !isRegisterRequest)
				|| (req.getRequestURI().matches(".*(css|js)"))) {
			chain.doFilter(request, response);

		} else if (isLoggedIn && (isLogInRequest || isRegisterRequest)) {
			res.sendRedirect("/DoAn_Store/");
		} else {
			res.sendRedirect("/DoAn_Store/login");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
