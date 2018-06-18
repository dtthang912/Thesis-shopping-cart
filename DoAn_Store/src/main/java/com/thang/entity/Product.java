package com.thang.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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

@Entity
@Table(name = "product")
@NamedQueries({ @NamedQuery(name = "Product.countAll", query = "SELECT COUNT(*) FROM Product p"),
		@NamedQuery(name = "Product.getAll", query = "SELECT p FROM Product p"),
		@NamedQuery(name = "Product.getByIds", query = "SELECT p FROM Product p WHERE p.id IN :ids"),
		@NamedQuery(name = "Product.getLatestProducts", query = "SELECT p FROM Product p ORDER BY p.id DESC") })
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1010608974797417908L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "price")
	private float price;

	@Column(name = "quantity")
	private int quantity;

	@JoinColumn(name = "idCategory", referencedColumnName = "id")
	@ManyToOne
	private Category category;

	@JoinColumn(name = "idDistributor", referencedColumnName = "id")
	@ManyToOne
	private Distributor distributor;

	@JoinColumn(name = "idProduct", referencedColumnName = "id")
	@OneToMany(fetch = FetchType.EAGER)
	private List<ProductTranslation> productTranslationList;

	@Column(name = "imageUrl")
	private String imageUrl;

	public Product() {

	}

	public Product(float price, int quantity, Category category, Distributor distributor,
			List<ProductTranslation> productTranslationList, String imageUrl) {
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.distributor = distributor;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public float getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public Category getCategory() {
		return category;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<ProductTranslation> getProductTranslationList() {
		return productTranslationList;
	}

	public void setProductTranslationList(List<ProductTranslation> productTranslationList) {
		this.productTranslationList = productTranslationList;
	}

}
