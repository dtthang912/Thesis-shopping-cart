package com.thang.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDAO<T>{
	private Class<T> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void create(T entity) {
		Session session = getSession();
		session.save(entity);
	}

	
	public void delete(T entity) {
		Session session = getSession();
		session.remove(entity);
	}

	
	public void update(T entity) {
		Session session = getSession();
		session.update(entity);
	}

	
	public T find(Integer id) {
		Session session = getSession();
		return session.get(entityClass, id);
	}

	
	public void updateList(List<T> entityList) {
		Session session = getSession();
		for (T entity : entityList) {
			session.update(entity);
		}
	}

	
	public void deleteList(List<T> entityList) {
		Session session = getSession();
		for (T entity : entityList) {
			session.remove(entity);
		}
	}
}
