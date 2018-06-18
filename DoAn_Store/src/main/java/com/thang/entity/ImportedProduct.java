package com.thang.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "imported_product")
@NamedQueries({ @NamedQuery(name = "ImportedProduct.countAll", query = "SELECT COUNT(*) FROM ImportedProduct i"),
	@NamedQuery(name = "ImportedProduct.getAll", query = "SELECT i FROM ImportedProduct i"),
	@NamedQuery(name = "ImportedProduct.getByDay", query = "SELECT i FROM ImportedProduct i WHERE CURRENT_DATE = DATE(i.date)"),
	@NamedQuery(name = "ImportedProduct.getByMonth", query = "SELECT i FROM ImportedProduct i WHERE MONTH(CURRENT_DATE) = MONTH(i.date) AND YEAR(CURRENT_DATE) = YEAR(i.date)"),
	@NamedQuery(name = "ImportedProduct.getByYear", query = "SELECT i FROM ImportedProduct i WHERE YEAR(CURRENT_DATE) = YEAR(i.date)")})
public class ImportedProduct implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4585720684143862353L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "idProduct", referencedColumnName = "id")
	@ManyToOne
	private Product product;

	@Column(name = "price")
	private float price;

	@Column(name = "date")
	private Date date;

	public int getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public float getPrice() {
		return price;
	}

	public Date getDate() {
		return date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
