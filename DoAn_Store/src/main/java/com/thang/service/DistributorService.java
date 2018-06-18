package com.thang.service;

import java.util.List;

import com.thang.entity.Distributor;

public interface DistributorService {

	List<Distributor> getDistributorsByCriteria(String productName, String categoryTitle);

}