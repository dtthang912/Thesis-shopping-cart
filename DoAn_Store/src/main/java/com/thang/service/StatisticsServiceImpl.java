package com.thang.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thang.dto.SoldProductInfo;
import com.thang.dto.Statistics;
import com.thang.entity.ImportedProduct;
import com.thang.entity.LineItem;
import com.thang.entity.Order;
import com.thang.repository.ImportedProductDAO;
import com.thang.repository.OrderDAO;

@Service("statisticsServiceImpl")
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ImportedProductDAO importedProductDAO;

	@Override
	@Transactional(readOnly = true)
	public Statistics analyzeStatistically(String time) {
		Statistics statistics = new Statistics();
		int revenue = 0;
		int cost = 0;
		Map<Integer, SoldProductInfo> soldProductInfoCollection = new HashMap<>();
		List<Order> orderList;
		List<ImportedProduct> importedProductList = importedProductDAO.getByDay();
		
		if("Day".equals(time)){
			orderList = orderDAO.getByDay();
			importedProductList = importedProductDAO.getByDay();
		} else if("Year".equals(time)){
			orderList = orderDAO.getByYear();
			importedProductList = importedProductDAO.getByYear();
		}
		else /* if time is month */{
			orderList = orderDAO.getByMonth();
			importedProductList = importedProductDAO.getByMonth();
		}
		for (Order order : orderList) {
			for (LineItem lineItem : order.getLineItems()) {
				revenue += lineItem.getPrice();
				Integer productId = lineItem.getProduct().getId();
				if (soldProductInfoCollection.containsKey(productId)) {
					SoldProductInfo soldProductInfo = soldProductInfoCollection.get(productId);
					soldProductInfo.setQuantity(soldProductInfo.getQuantity() + lineItem.getQuantity());
					soldProductInfo.setIncome(soldProductInfo.getIncome() + lineItem.getPrice() * lineItem.getQuantity());
					soldProductInfoCollection.put(productId, soldProductInfo);
				}
				else{
					SoldProductInfo soldProductInfo = new SoldProductInfo();
					soldProductInfo.setProductId(productId);
					soldProductInfo.setName(lineItem.getProduct().getProductTranslationList().get(0).getName());
					soldProductInfo.setQuantity(lineItem.getQuantity());
					soldProductInfo.setIncome(lineItem.getPrice() * lineItem.getQuantity());
					soldProductInfoCollection.put(productId, soldProductInfo);
				}
			}
		}
		for(ImportedProduct importedProduct : importedProductList){
			cost += importedProduct.getPrice();
		}
		List<SoldProductInfo> soldProductInfoList = new ArrayList<>(soldProductInfoCollection.values());
		Collections.sort(soldProductInfoList, new Comparator<SoldProductInfo>(){

			@Override
			public int compare(SoldProductInfo o1, SoldProductInfo o2) {
				return -Integer.compare(o1.getQuantity(), o2.getQuantity());
			}
			
		});
		
		int size = soldProductInfoList.size() > 10 ? 10 : soldProductInfoList.size();
		statistics.setRevenue(revenue);
		statistics.setProfit(revenue - cost);
		statistics.setSoldProductInfoList(soldProductInfoList.subList(0, size));
		return statistics;
	}
}
