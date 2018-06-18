package com.thang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thang.entity.Distributor;
import com.thang.repository.DistributorDAO;

@Service("distributorService")
public class DistributorServiceImpl implements DistributorService {
	@Autowired
	private DistributorDAO distributorDAO;
	
	@Autowired
	private ProductRelatedService productRelatedService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Distributor> getDistributorsByCriteria(String productName, String categoryTitle){
		List<String> leafCategoryTitles;

		if (categoryTitle.trim().equals("") || categoryTitle.equalsIgnoreCase("All")) {
			leafCategoryTitles = productRelatedService.getAllLeafCategoryTitles();
		} else {
			leafCategoryTitles = productRelatedService.getLeafCategoryTitles(categoryTitle);
		}
		
		List<Distributor> distributorList = distributorDAO.getByCriteria(productName, leafCategoryTitles);
		return distributorList;
	}
}
