package com.thang.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
@NamedQueries({
	@NamedQuery(name = "Category.countAll", query = "SELECT count(*) FROM Category c"),
	@NamedQuery(name = "Category.getAll", query = "SELECT c FROM Category c"),
	@NamedQuery(name = "Category.getByName", query = "SELECT c FROM Category c INNER JOIN c.categoryTranslationList ct WHERE ct.title = :name"),
	@NamedQuery(name = "Category.getRootList", query = "SELECT c FROM Category c WHERE c.parentCategory is null")
})
public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8338409598843938391L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "idCategory", referencedColumnName = "id")
	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CategoryTranslation> categoryTranslationList;

	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name = "parentId")
	private Category parentCategory;
	
	@JsonIgnore 
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "parentCategory")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Category> subcategoryList;
	
	public Category() {
		
	}

	public Category(Category parentCategory, List<Category> subcategoryList, List<CategoryTranslation> categoryTranslationList) {
		this.parentCategory = parentCategory;
		this.subcategoryList = subcategoryList;
		this.categoryTranslationList = categoryTranslationList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public List<Category> getSubcategoryList() {
		return subcategoryList;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public void setSubcategoryList(List<Category> subcategoryList) {
		this.subcategoryList = subcategoryList;
	}

	public List<CategoryTranslation> getCategoryTranslationList() {
		return categoryTranslationList;
	}

	public void setCategoryTranslationList(List<CategoryTranslation> categoryTranslationList) {
		this.categoryTranslationList = categoryTranslationList;
	}
	
}
