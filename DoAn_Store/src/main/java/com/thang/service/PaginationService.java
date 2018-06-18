package com.thang.service;

import java.util.List;

public interface PaginationService {

	List<Integer> calculatePageIndexes(int currentPageIndex, int maxPageIndex);

}