package com.thang.entity;

import java.io.Serializable;

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

import org.hibernate.annotations.Type;

@Entity
@Table(name = "sale_product")
@NamedQueries({
	@NamedQuery(name = "SaleProduct.countAll", query = "SELECT count(*) FROM SaleProduct s WHERE s.deleted IS false"),
	@NamedQuery(name = "SaleProduct.getAll", query = "SELECT s FROM SaleProduct s WHERE s.deleted IS false"),
	@NamedQuery(name = "SaleProduct.getByIds", query = "SELECT s FROM SaleProduct s WHERE s.id IN :ids AND s.deleted IS false"),
	@NamedQuery(name = "SaleProduct.getValidList", query = "SELECT s FROM SaleProduct s WHERE (NOW() BETWEEN s.event.dateStart AND s.event.dateEnd) AND s.deleted IS false ORDER BY s.id DESC")
})
public class SaleProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1935255229452032941L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "idProduct", referencedColumnName = "id")
	@ManyToOne
	private Product product;

	@Column(name = "originalPrice")
	private float originalPrice;

	@Column(name = "salePrice")
	private float salePrice;

	@JoinColumn(name = "idEvent", referencedColumnName = "id")
	@ManyToOne
	private Event event;

	@Column(name = "deleted")
	@Type(type = "numeric_boolean")
	protected boolean deleted;
	
	public SaleProduct() {

	}

	public SaleProduct(Product product, float originalPrice, float salePrice, Event event) {
		this.product = product;
		this.originalPrice = originalPrice;
		this.salePrice = salePrice;
		this.event = event;
	}

	public int getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
