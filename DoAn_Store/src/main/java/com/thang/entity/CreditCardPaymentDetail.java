package com.thang.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card_payment_detail")
public class CreditCardPaymentDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7544275945069233977L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "idOrder", referencedColumnName = "id")
	@OneToOne
	private Order order;

	@JoinColumn(name = "idCreditCard", referencedColumnName = "id")
	@ManyToOne
	private CreditCard creditCard;

	public CreditCardPaymentDetail() {
	}

	public CreditCardPaymentDetail(int id, Order order, CreditCard creditCard) {
		this.id = id;
		this.order = order;
		this.creditCard = creditCard;
	}

	public int getId() {
		return id;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
