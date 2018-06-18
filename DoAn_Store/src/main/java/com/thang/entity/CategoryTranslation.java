package com.thang.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "category_translation")
@NamedQueries({
	@NamedQuery(name = "CategoryTranslation.countAll", query = "SELECT count(*) FROM CategoryTranslation ct"),
	@NamedQuery(name = "CategoryTranslation.getAll", query = "SELECT ct FROM CategoryTranslation ct"),
})
public class CategoryTranslation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 470709108739003364L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "language")
	private String language;
	
	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	public CategoryTranslation() {

	}

	public CategoryTranslation(String language, String title, String description) {
		this.language = language;
		this.title = title;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getLanguage() {
		return language;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
