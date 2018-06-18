package com.thang.dto;

import java.util.List;

public class Statistics {
	private float revenue;
	private float profit;
	private List<SoldProductInfo> soldProductInfoList;
	public Statistics() {
		
	}
	public float getRevenue() {
		return revenue;
	}
	public float getProfit() {
		return profit;
	}
	public List<SoldProductInfo> getSoldProductInfoList() {
		return soldProductInfoList;
	}
	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}
	public void setProfit(float profit) {
		this.profit = profit;
	}
	public void setSoldProductInfoList(List<SoldProductInfo> soldProductInfoList) {
		this.soldProductInfoList = soldProductInfoList;
	}
}
