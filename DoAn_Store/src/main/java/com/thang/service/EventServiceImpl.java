package com.thang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thang.config.GeneralPageConfig;
import com.thang.entity.Event;
import com.thang.repository.EventDAO;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private PaginationService paginationService;

	@Override
	@Transactional
	public void create(Event event) {
		eventDAO.create(event);
	}
	
	@Override
	@Transactional
	public void edit(Event event) {
		eventDAO.update(event);
	}
	
	@Override
	@Transactional(readOnly = true)
	public int getMaxPageIndex() { 
		return (int) eventDAO.countAll() / GeneralPageConfig.NO_OF_EVENT_RECORDS_PER_PAGE + 1;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Integer> getPageIndexes(int currentPageIndex) {
		return paginationService.calculatePageIndexes(currentPageIndex, getMaxPageIndex());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Event> getEventsForPagination(int currentPageIndex) {
		List<Event> eventList = eventDAO.getAll(
				(currentPageIndex - 1) * GeneralPageConfig.NO_OF_EVENT_RECORDS_PER_PAGE,
				GeneralPageConfig.NO_OF_EVENT_RECORDS_PER_PAGE);
		return eventList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Event> getByIds(List<Integer> ids) {
		return eventDAO.getByIds(ids);
	}

	@Override
	@Transactional
	public void editList(List<Event> eventList) {
		eventDAO.updateList(eventList);
	}

	@Override
	@Transactional
	public void deleteList(List<Event> eventList) {
		eventDAO.deleteList(eventList);
		
	}

	@Override
	@Transactional
	public Event getById(Integer id) {
		return eventDAO.find(id);
	}

	@Override
	@Transactional
	public List<Event> getValidList() {
		return eventDAO.getValidList();
	}
	
}
