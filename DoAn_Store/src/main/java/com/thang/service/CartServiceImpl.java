package com.thang.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.thang.entity.LineItem;
import com.thang.entity.Product;
import com.thang.entity.SaleProduct;
import com.thang.repository.ProductDAO;
import com.thang.repository.SaleProductDAO;

@Service("cartService")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private SaleProductDAO saleProductDAO;

	private List<LineItem> lineItemList;

	@PostConstruct
	public void init() {
		lineItemList = new ArrayList<>();
	}

	@Override
	@Transactional(readOnly = true)
	public void addProductToCart(int productId, int quantity) {
		Product product = productDAO.find(productId);

		for (int i = 0; i < lineItemList.size(); i++) {
			if (lineItemList.get(i).getProduct().getId() == product.getId()) {
				lineItemList.get(i).setQuantity(lineItemList.get(i).getQuantity() + quantity);
				return;
			}
		}

		// if product is not in cart
		float currentPrice = product.getPrice();
		List<SaleProduct> saleProductList = saleProductDAO.getSaleProducts();

		for (SaleProduct saleProduct : saleProductList) {
			if (saleProduct.getProduct().getId() == product.getId()) {
				currentPrice = saleProduct.getSalePrice();
			}
		}

		LineItem lineItem = new LineItem();
		lineItem.setProduct(product);
		lineItem.setQuantity(quantity);
		lineItem.setPrice(currentPrice);
		lineItemList.add(lineItem);
	}

	@Override
	public void editCart(int productId, int quantity) {
		for (int i = 0; i < lineItemList.size(); i++) {
			if (lineItemList.get(i).getProduct().getId() == productId && quantity != 0) {
				lineItemList.get(i).setQuantity(quantity);
				return;
			}
			else if(lineItemList.get(i).getProduct().getId() == productId){
				lineItemList.remove(i);
				return;
			}
		}
	}

	@Override
	public List<LineItem> getLineItemList() {
		return lineItemList;
	}

	@Override
	public void removeProductInCart(int productId) {
		for (int i = 0; i < lineItemList.size(); i++) {
			if (lineItemList.get(i).getProduct().getId() == productId) {
				lineItemList.remove(i);
				return;
			}
		}
	}

	@Override
	public void removeAll() {
		lineItemList.clear();
	}
}
