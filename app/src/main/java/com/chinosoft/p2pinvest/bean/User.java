package com.chinosoft.p2pinvest.bean;

import java.io.Serializable;

public class User implements Serializable{
	private Integer id;
	private String userName;
	private Double totalMoney;
	private Double income;
	private Double rechage;
	private String userInfo;
	private String telePhone;
	private String email;
	private String password;
	private String gesture;
	private Integer bid;
	private Integer integral;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getRechage() {
		return rechage;
	}

	public void setRechage(Double rechage) {
		this.rechage = rechage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getGesture() {
		return gesture;
	}

	public void setGesture(String gesture) {
		this.gesture = gesture;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", totalMoney=" + totalMoney +
				", income=" + income +
				", rechage=" + rechage +
				", userInfo='" + userInfo + '\'' +
				", telePhone='" + telePhone + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", gesture='" + gesture + '\'' +
				", bid=" + bid +
				", integral=" + integral +
				'}';
	}
}

