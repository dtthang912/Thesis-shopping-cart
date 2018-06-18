package com.thang.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_translation")
public class ProductTranslation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7151223131587501525L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "language")
	private String language;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	public ProductTranslation() {

	}

	public ProductTranslation(String name, String description, String language) {
		this.name = name;
		this.description = description;
		this.language = language;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getLanguage() {
		return language;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}

