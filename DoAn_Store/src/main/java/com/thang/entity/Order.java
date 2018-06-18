package com.thang.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "`order`")
@NamedQueries({ @NamedQuery(name = "Order.countAll", query = "SELECT COUNT(*) FROM Order o"),
		@NamedQuery(name = "Order.getAll", query = "SELECT o FROM Order o"),
		@NamedQuery(name = "Order.getByDay", query = "SELECT o FROM Order o WHERE o.status = 'RECEIVED' AND CURRENT_DATE = DATE(o.date)"),
		@NamedQuery(name = "Order.getByMonth", query = "SELECT o FROM Order o WHERE o.status = 'RECEIVED' AND MONTH(CURRENT_DATE) = MONTH(o.date) AND YEAR(CURRENT_DATE) = YEAR(o.date)"),
		@NamedQuery(name = "Order.getByYear", query = "SELECT o FROM Order o WHERE o.status = 'RECEIVED' AND YEAR(CURRENT_DATE) = YEAR(o.date)")
})
public class Order implements Serializable {

	private static final long serialVersionUID = -7849717953793181699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "idCustomer", referencedColumnName = "id")
	@ManyToOne
	private Customer customer;

	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date date;

	@Column(name = "num")
	private String num;

	@Column(name = "street")
	private String street;

	@Column(name = "distric")
	private String distric;

	@Column(name = "city")
	private String city;

	@Column(name = "additionalAddressInfo")
	private String additionalAddressInfo;

	@Column(name = "contactNumber")
	private int contactNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private OrderStatus status;

	@Enumerated(EnumType.STRING)
	@Column(name = "paymentType")
	private PaymentType paymentType;

	@JoinColumn(nullable = false, name = "idOrder", referencedColumnName = "id")
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<LineItem> lineItems;

	public Order() {

	}

	public Order(Customer customer, Date date, String num, String street, String distric, String city,
			String additionalAddressInfo, int contactNumber, OrderStatus status, PaymentType paymentType,
			List<LineItem> lineItems) {
		this.customer = customer;
		this.date = date;
		this.num = num;
		this.street = street;
		this.distric = distric;
		this.city = city;
		this.additionalAddressInfo = additionalAddressInfo;
		this.contactNumber = contactNumber;
		this.status = status;
		this.paymentType = paymentType;
		this.lineItems = lineItems;
	}

	public int getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Date getDate() {
		return date;
	}

	public String getNum() {
		return num;
	}

	public String getStreet() {
		return street;
	}

	public String getDistric() {
		return distric;
	}

	public String getCity() {
		return city;
	}

	public String getAdditionalAddressInfo() {
		return additionalAddressInfo;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setDistric(String distric) {
		this.distric = distric;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAdditionalAddressInfo(String additionalAddressInfo) {
		this.additionalAddressInfo = additionalAddressInfo;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

}
