package com.thang.service;

import java.util.List;

import com.thang.entity.Event;

public interface EventService {

	void create(Event event);

	void edit(Event event);

	int getMaxPageIndex();

	List<Integer> getPageIndexes(int currentPageIndex);

	List<Event> getEventsForPagination(int currentPageIndex);

	List<Event> getByIds(List<Integer> ids);

	void editList(List<Event> eventList);

	void deleteList(List<Event> eventList);
	
	Event getById(Integer id);
	
	List<Event> getValidList();

}