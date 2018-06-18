package com.thang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thang.config.GeneralPageConfig;
import com.thang.config.HomePageConfig;
import com.thang.entity.Category;
import com.thang.entity.Product;
import com.thang.repository.CategoryDAO;
import com.thang.repository.ProductDAO;

@Service("productRelatedService")
public class ProductRelatedServiceImpl implements ProductRelatedService {

	@Autowired
	private PaginationService paginationService;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Product> getLatestProducts() {
		return productDAO.getLatestProducts(HomePageConfig.NO_OF_DISPLAY_LASTEST_PRODUCT);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> getRootCategoryList() {
		return categoryDAO.getRootList();
	}

	@Override
	@Transactional(readOnly = true)
	public Product getProductById(int id) {
		return productDAO.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public int getMaxPageIndex(String name, String categoryTitle, String distributor) {
		List<String> leafCategoryTitles;

		if (categoryTitle.trim().equals("") || categoryTitle.equalsIgnoreCase("All")) {
			leafCategoryTitles = getAllLeafCategoryTitles();
		} else {
			leafCategoryTitles = getLeafCategoryTitles(categoryTitle);
		}
		return (int) productDAO.countByCriteria(name, leafCategoryTitles, distributor)
				/ GeneralPageConfig.NO_OF_PRODUCT_RECORDS_PER_PAGE + 1;
	}

	/***
	 * Get the page indexes for pagination
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Integer> getPageIndexes(String name, String categoryTitle, String distributor, int currentPageIndex) {
		return paginationService.calculatePageIndexes(currentPageIndex, getMaxPageIndex(name, categoryTitle, distributor));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getProductsForPagination(String name, String categoryTitle, String distributor,
			int currentPageIndex) {
		List<String> leafCategoryTitles = new ArrayList<>();

		if (categoryTitle.trim().equals("") || categoryTitle.equalsIgnoreCase("All")) {
			leafCategoryTitles = getAllLeafCategoryTitles();
		} else {
			leafCategoryTitles = getLeafCategoryTitles(categoryTitle);
		}

		List<Product> productList = productDAO.getByCriteriaInPagination(name, leafCategoryTitles, distributor,
				(currentPageIndex - 1) * GeneralPageConfig.NO_OF_PRODUCT_RECORDS_PER_PAGE,
				GeneralPageConfig.NO_OF_PRODUCT_RECORDS_PER_PAGE);
		return productList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getLeafCategoryTitles(String categoryTitle) {
		List<String> leafCategoryTitles = new ArrayList<>();
		Category category = categoryDAO.getByName(categoryTitle);
		if (category.getSubcategoryList().isEmpty()) {
			leafCategoryTitles.add(categoryTitle);
		}
		if (!category.getSubcategoryList().isEmpty()) {
			for (Category subcategory : category.getSubcategoryList()) {
				leafCategoryTitles
						.addAll(getLeafCategoryTitles(subcategory.getCategoryTranslationList().get(0).getTitle()));
			}
		}
		return leafCategoryTitles;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getAllLeafCategoryTitles() {
		List<String> leafCategoryTitles = new ArrayList<>();
		List<Category> categoryList = categoryDAO.getRootList();
		for (Category category : categoryList) {
			leafCategoryTitles.addAll(getLeafCategoryTitles(category.getCategoryTranslationList().get(0).getTitle()));
		}
		return leafCategoryTitles;
	}
}
