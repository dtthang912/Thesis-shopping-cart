package com.thang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thang.config.GeneralPageConfig;

@Service("paginationService")
public class PaginationServiceImpl implements PaginationService {
	@Override
	public List<Integer> calculatePageIndexes(int currentPageIndex, int maxPageIndex) {
		List<Integer> pageIndexes = new ArrayList<Integer>();
		int minDisplayIndex = 0;
		int maxDisplayIndex = 0;

		if ((maxPageIndex - currentPageIndex) <= (GeneralPageConfig.MAX_DISPLAY_PAGE_INDEXES / 2)) {
			minDisplayIndex = maxPageIndex < GeneralPageConfig.MAX_DISPLAY_PAGE_INDEXES ? 1
					: maxPageIndex - GeneralPageConfig.MAX_DISPLAY_PAGE_INDEXES + 1;
			maxDisplayIndex = maxPageIndex;
		} else if (currentPageIndex <= GeneralPageConfig.MAX_DISPLAY_PAGE_INDEXES / 2 + 1) {
			minDisplayIndex = 1;
			maxDisplayIndex = maxPageIndex < GeneralPageConfig.MAX_DISPLAY_PAGE_INDEXES ? maxPageIndex : GeneralPageConfig.MAX_DISPLAY_PAGE_INDEXES;
		} else {
			minDisplayIndex = currentPageIndex - GeneralPageConfig.MAX_DISPLAY_PAGE_INDEXES / 2;
			maxDisplayIndex = minDisplayIndex + GeneralPageConfig.MAX_DISPLAY_PAGE_INDEXES - 1;
		}

		for (int i = minDisplayIndex; i <= maxDisplayIndex; i++) {
			pageIndexes.add(i);
		}
		return pageIndexes;
	}
}
