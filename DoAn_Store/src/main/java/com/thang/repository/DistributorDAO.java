package com.thang.repository;

import java.util.List;

import com.thang.entity.Distributor;

public interface DistributorDAO {

	List<Distributor> getAll();

	List<Distributor> getByCriteria(String name, List<String> categoryTitles);

}