package com.thang.form;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.thang.entity.Event;

public class EventForm {
	private int ePage = 1;
	
	//event's properties
	private String name;
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateStart;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateEnd;

	private List<String> selectedIds;
	
	// List of events for editing
	private List<Event> eventList;

	// Method for processing
	private String method;

	public EventForm() {
		
	}

	public int getePage() {
		return ePage;
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

	public List<String> getSelectedIds() {
		return selectedIds;
	}

	public List<Event> getEventList() {
		return eventList;
	}

	public String getMethod() {
		return method;
	}

	public void setePage(int ePage) {
		this.ePage = ePage;
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

	public void setSelectedIds(List<String> selectedIds) {
		this.selectedIds = selectedIds;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
}
