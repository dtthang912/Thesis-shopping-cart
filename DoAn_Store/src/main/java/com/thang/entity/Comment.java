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

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "comment")
@NamedQueries({ @NamedQuery(name = "Comment.countAll", query = "SELECT COUNT(*) FROM Comment c WHERE c.deleted IS false"),
	@NamedQuery(name = "Comment.getAll", query = "SELECT c FROM Comment c WHERE c.deleted IS false"),
	@NamedQuery(name = "Comment.getByProductId", query = "SELECT c FROM Comment c WHERE c.product.id = :id AND c.deleted IS false"),
	@NamedQuery(name = "Comment.getByIds", query = "SELECT c FROM Comment c WHERE c.id IN :ids AND c.deleted IS false")})
public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7401430564902683664L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "idCustomer", referencedColumnName = "id")
	@ManyToOne
	private Customer customer;

	@JoinColumn(name = "idProduct", referencedColumnName = "id")
	@ManyToOne
	private Product product;

	@Column(name = "content")
	private String content;

	@Column(name = "time")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date time;

	@Column(name = "deleted")
	@Type(type = "numeric_boolean")
	protected boolean deleted;
	
	public Comment() {

	}

	public Comment(Customer customer, Product product, String content, Date time) {
		this.customer = customer;
		this.product = product;
		this.content = content;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Product getProduct() {
		return product;
	}

	public String getContent() {
		return content;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
