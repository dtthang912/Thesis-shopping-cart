package com.thang.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.thang.entity.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl extends GenericDAO<Category> implements CategoryDAO {

	public CategoryDAOImpl() {
		super(Category.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Category> getRootList() {
		Session session = getSession();
		List<Category> rootCategoryList = session.getNamedQuery("Category.getRootList").getResultList();
		return rootCategoryList;
	}

	@Override
	public Category getByName(String name) {
		Session session = getSession();
		return (Category) session.getNamedQuery("Category.getByName").setParameter("name", name).getSingleResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Category> getAll() {
		Session session = getSession();
		List<Category> categoryList = session.getNamedQuery("Category.getAll").getResultList();
		return categoryList;
	}
}
