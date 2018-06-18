package com.thang.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Thu {
	public static List<Product> getProducts(Session session,Category category){ 
		List<Product> productList = new ArrayList<>();
//		productList.addAll(session.getNamedQuery("Product.getByCriteria").setParameter("name", "%%").setParameter("distributor", "%%").setParameter("category", "%"+ category.getTitle() +"%").getResultList());
		if(category.getSubcategoryList() != null){
			for(Category subCategory : category.getSubcategoryList()){
				productList.addAll(getProducts(session,subCategory));
			}
		}
		return productList;
	}
	public static void main(String[] args){
        Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
//		List<Product> productList = new ArrayList<>();
//		StringBuilder builder = new StringBuilder();
//		List<String> categoryTitles= new ArrayList<>();
//		categoryTitles.add("Máy giặt");
//		categoryTitles.add("�?iện thoại di động");
//		for (int i = 0; i < categoryTitles.size(); i++) {
//			builder.append("c.title = ? OR ");
//		}
//		NativeQuery<Product> nativeQuery = session
//				.createNativeQuery("SELECT p.* FROM product p INNER JOIN category c ON p.idCategory = c.id "
//						+ "INNER JOIN distributor d ON p.idDistributor = d.id INNER JOIN product_translation pt ON pt.idProduct = p.id"
//						+ " WHERE (pt.name like ? AND d.name = ?) AND ("
//						+ builder.delete(builder.length() - 4,builder.length() - 1).toString() + ")", Product.class);
//		nativeQuery.setParameter(1, "%%").setParameter(2, "lg");
//		for (int i = 0; i < categoryTitles.size(); i++) {
//			nativeQuery.setParameter(i + 3, categoryTitles.get(i));
//		}
//		nativeQuery.setFirstResult(0)
//		.setMaxResults(100);
//		productList = nativeQuery.getResultList();
//		List<Order> orderList = session.getNamedQuery("Order.getByYear").getResultList();
//		for(Order order : orderList)
//		System.out.println(order.getId());
		List<Category> cl = session.getNamedQuery("Category.get").getResultList();
		System.out.println(cl.size());
	}
	}
