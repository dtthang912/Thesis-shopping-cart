package com.thang.util;

import java.util.List;

import com.thang.entity.Category;
import com.thang.entity.CategoryTranslation;
import com.thang.entity.Product;
import com.thang.entity.ProductTranslation;
import com.thang.entity.SaleProduct;

public final class ViewUtilities {

	private ViewUtilities() {

	}

	public static String getProductNameByLanguage(Product product, String language) {
		String productName = "";
		String defaultProductName = "";
		for (ProductTranslation productTranslation : product.getProductTranslationList()) {
			if (productTranslation.getLanguage().equals(language)) {
				productName = productTranslation.getName();
			}
			if (productTranslation.getLanguage().equals("en")) {
				defaultProductName = productTranslation.getName();
			}
		}
		if (productName == null || productName.isEmpty()) {
			productName = defaultProductName;
		}
		return productName;
	}
	
	public static String getProductDescriptionByLanguage(Product product, String language) {
		String productDescription = "";
		String defaultProductDescription = "";
		for (ProductTranslation productTranslation : product.getProductTranslationList()) {
			if (productTranslation.getLanguage().equals(language)) {
				productDescription = productTranslation.getDescription();
			}
			if (productTranslation.getLanguage().equals("en")) {
				defaultProductDescription = productTranslation.getDescription();
			}
		}
		if (productDescription == null || productDescription.isEmpty()) {
			productDescription = defaultProductDescription;
		}
		return productDescription;
	}

	public static String getCategoryTitleByLanguage(Category category, String language) {
		String categoryTitle = "";
		String defaultCategoryTitle = "";
		for (CategoryTranslation categoryTranslation : category.getCategoryTranslationList()) {
			if (categoryTranslation.getLanguage().equals(language)) {
				categoryTitle = categoryTranslation.getTitle();
			}
			if (categoryTranslation.getLanguage().equals("en")) {
				defaultCategoryTitle = categoryTranslation.getTitle();
			}
		}
		if (categoryTitle == null || categoryTitle.isEmpty()) {
			categoryTitle = defaultCategoryTitle;
		}
		return categoryTitle;
	}
	
	/***
	 * 
	 * Return the product price include sale event
	 */
	public static String getCurrentPrice(Product product, List<SaleProduct> saleProductList){
		for(SaleProduct saleProduct : saleProductList){
			if(product.getId() == saleProduct.getProduct().getId()){
				return "" + saleProduct.getSalePrice() + " đ";
			}
		}
		return "" + product.getPrice() + " đ";
	}
	
	/***
	 * 
	 * Return the original product price if there's an event
	 */
	public static String getOriginalPrice(Product product, List<SaleProduct> saleProductList){
		for(SaleProduct saleProduct : saleProductList){
			if(product.getId() == saleProduct.getProduct().getId()){
				return "" + product.getPrice() + " đ";
			}
		}
		return "";
	}
}
