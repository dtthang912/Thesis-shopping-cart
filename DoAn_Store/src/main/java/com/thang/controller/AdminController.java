package com.thang.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.thang.dto.Statistics;
import com.thang.entity.Comment;
import com.thang.entity.Customer;
import com.thang.entity.CustomerType;
import com.thang.entity.Event;
import com.thang.entity.Product;
import com.thang.entity.SaleProduct;
import com.thang.form.CommentForm;
import com.thang.form.CustomerForm;
import com.thang.form.EventForm;
import com.thang.form.ProductSearchForm;
import com.thang.form.SaleProductForm;
import com.thang.service.CommentService;
import com.thang.service.CustomerService;
import com.thang.service.EventService;
import com.thang.service.ProductRelatedService;
import com.thang.service.SaleProductService;
import com.thang.service.StatisticsService;

@Controller
public class AdminController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private StatisticsService statisticsService;

	@Autowired
	private EventService eventService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private ProductRelatedService productRelatedService;

	@Autowired
	private SaleProductService saleProductService;

	@GetMapping("employee/manageCustomer")
	public String manageCustomerGetRequest(@ModelAttribute("customerForm") CustomerForm customerForm, Model model,
			HttpServletResponse response) throws Exception {

		if ("showAdd".equals(customerForm.getMethod())) {
			return "addCustomer";
		} else /* if( method is list) */ {
			List<Customer> customerList = new ArrayList<>();
			List<Integer> pageIndexes = new ArrayList<>();

			try {
				if ((customerForm.getcPage() < 0) || (customerForm.getcPage() > customerService.getMaxPageIndex())) {
					throw new NumberFormatException();
				}

				// Calculate number of pages that will be displayed
				pageIndexes = customerService.getPageIndexes(customerForm.getcPage());
				customerList = customerService.getCustomersForPagination(customerForm.getcPage());

				model.addAttribute("pageIndexes", pageIndexes);
				model.addAttribute("customerList", customerList);
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			return "manageCustomer";
		}
	}

	@PostMapping("employee/manageCustomer")
	public String manageCustomerPostRequest(@Valid @ModelAttribute("customerForm") CustomerForm customerForm, BindingResult bindingResult,
			HttpServletRequest request) {System.out.println(customerForm.getMethod());
		if ("add".equals(customerForm.getMethod())) {
			Customer customer = new Customer();
			try {
				if(customerService.getByUserName(customerForm.getUserName()) != null){
					bindingResult.reject("account.existed");
					return "addCustomer";
				}
				BeanUtils.copyProperties(customer, customerForm);
				customer.setDateCreated(new Date());
				customer.setType(CustomerType.NORMAL);
			} catch (IllegalAccessException e) {
				
			} catch (InvocationTargetException e) {
				
			}
			customerService.create(customer);
			// Return to the last page index
			return "redirect:/employee/manageCustomer?method=list&cPage=" + customerService.getMaxPageIndex();
		} else if ("edit".equals(customerForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<Integer>();

			// Change format of id params to integer
			for (String selectedIdParam : customerForm.getSelectedIds()) {
				if (selectedIdParam != null) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<Customer> selectedCustomers = customerService.getByIds(selectedIds);
			customerForm.setCustomerList(selectedCustomers);

			return "editCustomer";
		} else if ("update".equals(customerForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<Integer>();
			for (String selectedIdParam : customerForm.getSelectedIds()) {
				if (selectedIdParam != null && !"null".equals(selectedIdParam)) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<Customer> selectedCustomers = customerService.getByIds(selectedIds);

			for (int i = 0; i < selectedCustomers.size(); i++) {
				selectedCustomers.get(i).setfName(customerForm.getCustomerList().get(i).getfName());
				selectedCustomers.get(i).setmName(customerForm.getCustomerList().get(i).getmName());
				selectedCustomers.get(i).setlName(customerForm.getCustomerList().get(i).getlName());
				selectedCustomers.get(i).setNum(customerForm.getCustomerList().get(i).getNum());
				selectedCustomers.get(i).setStreet(customerForm.getCustomerList().get(i).getStreet());
				selectedCustomers.get(i).setDistric(customerForm.getCustomerList().get(i).getDistric());
				selectedCustomers.get(i).setCity(customerForm.getCustomerList().get(i).getCity());
				selectedCustomers.get(i).setPhone(customerForm.getCustomerList().get(i).getPhone());
				selectedCustomers.get(i).setEmail(customerForm.getCustomerList().get(i).getEmail());
			}
			customerService.editList(selectedCustomers);
			return "redirect:/employee/manageCustomer?method=list&cPage=" + customerForm.getcPage();
		} else if ("delete".equals(customerForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<Integer>();

			// Change format of id params to integer
			for (String selectedIdParam : customerForm.getSelectedIds()) {
				if (selectedIdParam != null) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<Customer> selectedCustomers = customerService.getByIds(selectedIds);
			customerService.deleteList(selectedCustomers);
			return "redirect:/employee/manageCustomer?method=list&cPage=" + customerForm.getcPage();
		} else /* if( method is cancel) */ {
			return "redirect:/employee/manageCustomer?method=list&cPage=" + customerForm.getcPage();
		}
	}

	@GetMapping("employee/manageEvent")
	public String manageEventGetRequest(@ModelAttribute("eventForm") EventForm eventForm, Model model,
			HttpServletResponse response) throws Exception {

		if ("showAdd".equals(eventForm.getMethod())) {
			return "addEvent";
		} else /* if( method is list) */ {
			List<Event> eventList = new ArrayList<>();
			List<Integer> pageIndexes = new ArrayList<>();

			try {
				if ((eventForm.getePage() < 0) || (eventForm.getePage() > eventService.getMaxPageIndex())) {
					throw new NumberFormatException();
				}

				// Calculate number of pages that will be displayed
				pageIndexes = eventService.getPageIndexes(eventForm.getePage());
				eventList = eventService.getEventsForPagination(eventForm.getePage());

				model.addAttribute("pageIndexes", pageIndexes);
				model.addAttribute("eventList", eventList);
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			return "manageEvent";
		}
	}

	@PostMapping("employee/manageEvent")
	public String manageEventPostRequest(@ModelAttribute("eventForm") EventForm eventForm) {
		if ("add".equals(eventForm.getMethod())) {
			Event event = new Event();
			try {
				BeanUtils.copyProperties(event, eventForm);
			} catch (IllegalAccessException e) {

			} catch (InvocationTargetException e) {

			}
			eventService.create(event);

			// Return to the last page index
			return "redirect:/employee/manageEvent?method=list&ePage=" + eventService.getMaxPageIndex();
		} else if ("edit".equals(eventForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<Integer>();

			// Change format of id params to integer
			for (String selectedIdParam : eventForm.getSelectedIds()) {
				if (selectedIdParam != null) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<Event> selectedEvents = eventService.getByIds(selectedIds);
			eventForm.setEventList(selectedEvents);

			return "editEvent";
		} else if ("update".equals(eventForm.getMethod())) {
			eventService.editList(eventForm.getEventList());
			return "redirect:/employee/manageEvent?method=list&ePage=" + eventForm.getePage();
		} else if ("delete".equals(eventForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<Integer>();

			// Change format of id params to integer
			for (String selectedIdParam : eventForm.getSelectedIds()) {
				if (selectedIdParam != null) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<Event> selectedEvents = eventService.getByIds(selectedIds);
			eventService.deleteList(selectedEvents);
			return "redirect:/employee/manageEvent?method=list&ePage=" + eventForm.getePage();
		} else /* if( method is cancel) */ {
			return "redirect:/employee/manageEvent?method=list&ePage=" + eventForm.getePage();
		}
	}

	@GetMapping("employee/manageComment")
	public String manageCommentGetRequest(@ModelAttribute("commentForm") CommentForm commentForm, Model model,
			HttpServletResponse response) throws Exception {
		List<Comment> commentList = new ArrayList<>();
		List<Integer> pageIndexes = new ArrayList<>();

		try {
			if ((commentForm.getcPage() < 0) || (commentForm.getcPage() > commentService.getMaxPageIndex())) {
				throw new NumberFormatException();
			}

			// Calculate number of pages that will be displayed
			pageIndexes = commentService.getPageIndexes(commentForm.getcPage());
			commentList = commentService.getCommentsForPagination(commentForm.getcPage());

			model.addAttribute("pageIndexes", pageIndexes);
			model.addAttribute("commentList", commentList);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return "manageComment";
	}

	@PostMapping("employee/manageComment")
	public String manageCommentPostRequest(@Valid @ModelAttribute("commentForm") CommentForm commentForm) {
		if ("edit".equals(commentForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<Integer>();

			// Change format of id params to integer
			for (String selectedIdParam : commentForm.getSelectedIds()) {
				if (selectedIdParam != null) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<Comment> selectedComments = commentService.getByIds(selectedIds);
			commentForm.setCommentList(selectedComments);

			return "editComment";
		} else if ("update".equals(commentForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<Integer>();
			for (String selectedIdParam : commentForm.getSelectedIds()) {
				if (selectedIdParam != null && !"null".equals(selectedIdParam)) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<Comment> selectedComments = commentService.getByIds(selectedIds);

			for (int i = 0; i < selectedComments.size(); i++) {
				selectedComments.get(i).setContent(commentForm.getCommentList().get(i).getContent());
			}
			commentService.editList(selectedComments);
			return "redirect:/employee/manageComment?method=list&cPage=" + commentForm.getcPage();
		} else if ("delete".equals(commentForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<Integer>();

			// Change format of id params to integer
			for (String selectedIdParam : commentForm.getSelectedIds()) {
				if (selectedIdParam != null) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<Comment> selectedComments = commentService.getByIds(selectedIds);
			commentService.deleteList(selectedComments);
			return "redirect:/employee/manageComment?method=list&cPage=" + commentForm.getcPage();
		} else /* if( method is cancel) */ {
			return "redirect:/employee/manageComment?method=list&cPage=" + commentForm.getcPage();
		}
	}

	@GetMapping("employee/manageSaleProduct")
	public String manageSaleProductGetRequest(@ModelAttribute("saleProductForm") SaleProductForm saleProductForm,
			@ModelAttribute("productSearchForm") ProductSearchForm productForm, Model model,
			HttpServletResponse response) throws Exception {
		if ("findAdd".equals(saleProductForm.getMethod())) {
			List<Product> productList = new ArrayList<>();
			List<Integer> pageIndexes = new ArrayList<>();

			try {
				if ((productForm.getPage() < 0)
						|| (productForm.getPage() > productRelatedService.getMaxPageIndex("", "", ""))) {
					throw new NumberFormatException();
				}

				// Calculate number of pages that will be displayed
				pageIndexes = productRelatedService.getPageIndexes("", "", "", productForm.getPage());
				productList = productRelatedService.getProductsForPagination("", "", "", productForm.getPage());

				model.addAttribute("pageIndexes", pageIndexes);
				model.addAttribute("productList", productList);
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			return "getProduct";
		} else if ("showAdd".equals(saleProductForm.getMethod())) {
			Product product = productRelatedService.getProductById(productForm.getProductId());
			List<Event> validEventList = eventService.getValidList();
			model.addAttribute("validEventList", validEventList);
			model.addAttribute("product", product);
			return "addSaleProduct";
		} else /* if( method is list) */ {
			List<SaleProduct> saleProductList = new ArrayList<>();
			List<Integer> pageIndexes = new ArrayList<>();

			try {
				if ((saleProductForm.getsPage() < 0)
						|| (saleProductForm.getsPage() > saleProductService.getMaxPageIndex())) {
					throw new NumberFormatException();
				}

				// Calculate number of pages that will be displayed
				pageIndexes = saleProductService.getPageIndexes(saleProductForm.getsPage());
				saleProductList = saleProductService.getSaleProductsForPagination(saleProductForm.getsPage());

				model.addAttribute("pageIndexes", pageIndexes);
				model.addAttribute("saleProductList", saleProductList);
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			return "manageSaleProduct";
		}
	}

	@PostMapping("employee/manageSaleProduct")
	public String manageSaleProductPostRequest(@ModelAttribute("saleProductForm") SaleProductForm saleProductForm, Model model) {
		if ("add".equals(saleProductForm.getMethod())) {
			Product product = productRelatedService.getProductById(saleProductForm.getProductId());
			Event event = eventService.getById(saleProductForm.getEventId());
			SaleProduct saleProduct = new SaleProduct();
			saleProduct.setProduct(product);
			saleProduct.setEvent(event);
			saleProduct.setOriginalPrice(product.getPrice());
			saleProduct.setSalePrice(saleProductForm.getSalePrice());System.out.println(event.getDateEnd());
			saleProductService.create(saleProduct);

			// Return to the last page index
			return "redirect:/employee/manageSaleProduct?method=list&sPage=" + saleProductService.getMaxPageIndex();
		} else if ("edit".equals(saleProductForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<Integer>();

			// Change format of id params to integer
			for (String selectedIdParam : saleProductForm.getSelectedIds()) {
				if (selectedIdParam != null) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<Event> validEventList = eventService.getValidList();
			List<SaleProduct> selectedSaleProducts = saleProductService.getByIds(selectedIds);
			model.addAttribute("validEventList", validEventList);
			saleProductForm.setSaleProductList(selectedSaleProducts);

			return "editSaleProduct";
		} else if ("update".equals(saleProductForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<>();
			for (String selectedIdParam : saleProductForm.getSelectedIds()) {
				if (selectedIdParam != null && !"null".equals(selectedIdParam)) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<SaleProduct> selectedSaleProducts = saleProductService.getByIds(selectedIds);

			for (int i = 0; i < selectedSaleProducts.size(); i++) {
				selectedSaleProducts.get(i).setSalePrice(saleProductForm.getSaleProductList().get(i).getSalePrice());
				selectedSaleProducts.get(i)
						.setEvent(eventService.getById(saleProductForm.getSaleProductList().get(i).getEvent().getId()));
			}
			saleProductService.editList(selectedSaleProducts);
			return "redirect:/employee/manageSaleProduct?method=list&sPage=" + saleProductForm.getsPage();
		} else if ("delete".equals(saleProductForm.getMethod())) {
			List<Integer> selectedIds = new ArrayList<Integer>();

			// Change format of id params to integer
			for (String selectedIdParam : saleProductForm.getSelectedIds()) {
				if (selectedIdParam != null) {
					selectedIds.add(Integer.parseInt(selectedIdParam));
				}
			}

			List<SaleProduct> selectedSaleProducts = saleProductService.getByIds(selectedIds);
			saleProductService.deleteList(selectedSaleProducts);
			return "redirect:/employee/manageSaleProduct?method=list&sPage=" + saleProductForm.getsPage();
		} else /* if( method is cancel) */ {
			return "redirect:/employee/manageSaleProduct?method=list&sPage=" + saleProductForm.getsPage();
		}
	}
	
	@GetMapping("employee/statistics")
	public String analyzeStatistically(Model model, HttpServletRequest request) throws Exception {
			Statistics statistics = statisticsService.analyzeStatistically(request.getParameter("time"));
			model.addAttribute("statistics", statistics);
			return "statistics";
		}
	
}
