package com.thang.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "`event`")
@NamedQueries({ @NamedQuery(name = "Event.countAll", query = "SELECT COUNT(*) FROM Event e WHERE e.deleted IS false"),
	@NamedQuery(name = "Event.getAll", query = "SELECT e FROM Event e WHERE e.deleted IS false"),
	@NamedQuery(name = "Event.getByIds", query = "SELECT e FROM Event e WHERE e.id IN :ids AND e.deleted IS false"),
	@NamedQuery(name = "Event.getValidList", query = "SELECT e FROM Event e WHERE (NOW() <= e.dateEnd) AND e.deleted IS false")
})
public class Event implements Serializable{
	
	private static final long serialVersionUID = 8658116455164902872L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "dateStart")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateStart;

	@Column(name = "dateEnd")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateEnd;

	@Column(name = "deleted")
	@Type(type = "numeric_boolean")
	protected boolean deleted;
	
	public Event() {

	}

	public Event(String name, String description, Date dateStart, Date dateEnd) {
		this.name = name;
		this.description = description;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
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

	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
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

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
