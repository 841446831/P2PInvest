package com.chinosoft.p2pinvest.bean;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable{

	private Integer id;
	private String name; //名称
	private String title;  //副标题
	private Double annualRate;  //年利率
	private String timeLimit;  //期限
	private Integer investMoney;  //
	private String startTime;  //起息时间
	private Integer total;   //项目金额
	private Integer startMoney; //起购金额
	private Integer peopleNum;  //购买人数
	private String introduce;  //产品简介
	private String receivedWay;
	private Type type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getAnnualRate() {
		return annualRate;
	}
	public void setAnnualRate(Double annualRate) {
		this.annualRate = annualRate;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getReceivedWay() {
		return receivedWay;
	}

	public void setReceivedWay(String receivedWay) {
		this.receivedWay = receivedWay;
	}

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getStartMoney() {
		return startMoney;
	}
	public void setStartMoney(Integer startMoney) {
		this.startMoney = startMoney;
	}
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", title='" + title + '\'' +
				", annualRate=" + annualRate +
				", timeLimit='" + timeLimit + '\'' +
				", investMoney=" + investMoney +
				", startTime='" + startTime + '\'' +
				", total=" + total +
				", startMoney=" + startMoney +
				", peopleNum=" + peopleNum +
				", introduce='" + introduce + '\'' +
				", receivedWay='" + receivedWay + '\'' +
				", type=" + type +
				'}';
	}
}
