package com.thang.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.thang.entity.Category;
import com.thang.entity.Comment;
import com.thang.entity.Customer;
import com.thang.entity.Distributor;
import com.thang.entity.Product;
import com.thang.entity.SaleProduct;
import com.thang.form.CommentForm;
import com.thang.form.LoginForm;
import com.thang.form.ProductSearchForm;
import com.thang.service.CommentService;
import com.thang.service.DistributorService;
import com.thang.service.ProductRelatedService;
import com.thang.service.SaleProductService;

@Controller
@SessionAttributes("customer")
public class ProductDisplayController {

	@Autowired
	private ProductRelatedService productRelatedService;
	
	@Autowired
	private SaleProductService saleProductService;

	@Autowired
	private DistributorService distributorService;
	
	@Autowired
	private CommentService commentService;

	@GetMapping("/")
	public String processHomePage(@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, Model model) throws Exception {
		List<SaleProduct> saleProductList = saleProductService
				.getLatestSaleProducts();
		List<Product> latestProductList = productRelatedService
				.getLatestProducts();
		List<Category> rootCategoryList = productRelatedService.getRootCategoryList();
		model.addAttribute("saleProductList", saleProductList);
		model.addAttribute("latestProductList", latestProductList);
		model.addAttribute("rootCategoryList", rootCategoryList);
		return "index";
	}

	@GetMapping("/product-detail/{id}/{productName}")
	public String displayDetailProduct(@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, @ModelAttribute("commentForm") CommentForm commentForm,
			@PathVariable("id") int id, Model model) {
		Product product = productRelatedService.getProductById(id);
		List<Category> rootCategoryList = productRelatedService.getRootCategoryList();
		List<SaleProduct> saleProductList = saleProductService.getSaleProducts();
		List<Comment> commentList = commentService.getCommentsByProductId(id);
		model.addAttribute("product", product);
		model.addAttribute("rootCategoryList", rootCategoryList);
		model.addAttribute("saleProductList", saleProductList);
		model.addAttribute("commentList", commentList);
		return "detailProduct";
	}

	@PostMapping("/comment")
	public String postComment(@ModelAttribute("commentForm") CommentForm commentForm, @ModelAttribute("customer") Customer customer, Model model){
		commentService.postComment(customer,commentForm.getProductId(), commentForm.getContent());
		return "redirect:/product-detail/" + commentForm.getProductId()+"/" + commentForm.getProductNameUrl();
	}
	
	@GetMapping("/search")
	public String search(@ModelAttribute("productSearchForm") ProductSearchForm productSearchForm,
			@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpServletResponse response)
			throws IOException {
		List<Product> productList = new ArrayList<>();
		List<Integer> pageIndexes = new ArrayList<>();
		List<Category> rootCategoryList = productRelatedService.getRootCategoryList();
		List<Distributor> distributorList = new ArrayList<>();
		List<SaleProduct> saleProductList = new ArrayList<>();

		try {
			if ((productSearchForm.getPage() < 0) || (productSearchForm.getPage() > productRelatedService
					.getMaxPageIndex(productSearchForm.getName(), productSearchForm.getCategory(),
							productSearchForm.getDistributor()))) {
				throw new NumberFormatException();
			}

			// Calculate number of pages that will be displayed
			pageIndexes = productRelatedService.getPageIndexes(productSearchForm.getName(),
					productSearchForm.getCategory(), productSearchForm.getDistributor(), productSearchForm.getPage());
			productList = productRelatedService.getProductsForPagination(productSearchForm.getName(),
					productSearchForm.getCategory(), productSearchForm.getDistributor(), productSearchForm.getPage());
			distributorList = distributorService.getDistributorsByCriteria(productSearchForm.getName(),
					productSearchForm.getCategory());
			saleProductList = saleProductService.getSaleProducts();

			model.addAttribute("pageIndexes", pageIndexes);
			model.addAttribute("productList", productList);
			model.addAttribute("rootCategoryList", rootCategoryList);
			model.addAttribute("distributorList", distributorList);
			model.addAttribute("saleProductList", saleProductList);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return "products";
	}
}
