package com.chinosoft.p2pinvest.bean;

import java.io.Serializable;

/**
 * @author Administrator
 * 借款人
 */
public class Borrower implements Serializable{
	private int id;
	private Double annualRate; //年利率
	private Integer total;  
	private String timeLimit;
	private Integer investMoney;
	private String receivedWay;
	private Integer peopleNum;
	private BorrowPlatform borrowPlatform;
	@Override
	public String toString() {
		return "Borrower [id=" + id + ", annualRate=" + annualRate + ", total="
				+ total + ", timeLimit=" + timeLimit + ", investMoney="
				+ investMoney + ", receivedWay=" + receivedWay + ", peopleNum="
				+ peopleNum + ", borrowPlatform=" + borrowPlatform + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getAnnualRate() {
		return annualRate;
	}
	public void setAnnualRate(Double annualRate) {
		this.annualRate = annualRate;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	public Integer getInvestMoney() {
		return investMoney;
	}
	public void setInvestMoney(Integer investMoney) {
		this.investMoney = investMoney;
	}
	public String getReceivedWay() {
		return receivedWay;
	}
	public void setReceivedWay(String receivedWay) {
		this.receivedWay = receivedWay;
	}
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public BorrowPlatform getBorrowPlatform() {
		return borrowPlatform;
	}
	public void setBorrowPlatform(BorrowPlatform borrowPlatform) {
		this.borrowPlatform = borrowPlatform;
	}
	
}
