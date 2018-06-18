package com.thang.repository;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thang.entity.Event;

@Repository("eventDAO")
public class EventDAOImpl extends GenericDAO<Event> implements EventDAO {

	public EventDAOImpl() {
		super(Event.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> getAll(int offset, int limit) {
		Session session = getSession();
		List<Event> eventList = session.getNamedQuery("Event.getAll").setFirstResult(offset).setMaxResults(limit)
				.getResultList();
		return eventList;
	}

	@Override
	public long countAll() {
		Session session = getSession();
		try {
			return (Long) session.getNamedQuery("Event.countAll").getSingleResult();
		} catch (NoResultException e) {

		}
		return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> getByIds(List<Integer> ids) {
		Session session = getSession();
		List<Event> eventList = session.getNamedQuery("Event.getByIds").setParameter("ids", ids).getResultList();
		return eventList;
	}

	@Override
	public void deleteList(List<Event> eventList) {
		Session session = getSession();
		for (Event event : eventList) {
			event.setDeleted(true);
			session.update(event);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Event> getValidList() {
		Session session = getSession();
		List<Event> eventList = (List<Event>) session.getNamedQuery("Event.getValidList").getResultList();
		return eventList;
	}
}
