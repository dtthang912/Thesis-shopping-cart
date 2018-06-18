package com.thang.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5053994874498672019L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "num")
	private int num;

	@Column(name = "name")
	private String name;

	@Column(name = "expMonth")
	private int expMonth;

	@Column(name = "expYear")
	private int expYear;

	public CreditCard(int num, String name, int expMonth, int expYear) {
		this.num = num;
		this.name = name;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}

	public CreditCard() {

	}

	public int getId() {
		return id;
	}

	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

}
