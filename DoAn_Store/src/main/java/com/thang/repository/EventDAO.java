package com.thang.repository;

import java.util.List;

import com.thang.entity.Event;

public interface EventDAO {

	void create(Event entity);

	void delete(Event entity);

	void update(Event entity);

	Event find(Integer id);
	
	void updateList(List<Event> entityList);

	List<Event> getAll(int offset, int limit);

	long countAll();

	List<Event> getByIds(List<Integer> ids);

	void deleteList(List<Event> eventList);
	
	List<Event> getValidList();

}