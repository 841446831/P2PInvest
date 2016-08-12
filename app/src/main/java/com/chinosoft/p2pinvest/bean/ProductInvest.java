package com.chinosoft.p2pinvest.bean;

import java.io.Serializable;

public class ProductInvest implements Serializable{
	
	
	public Double getInvestMoney() {
		return investMoney;
	}
	public void setInvestMoney(Double investMoney) {
		this.investMoney = investMoney;
	}
	private Integer id;
	private Integer pid;
	private Integer uid;
	private Integer status;
	private Long startTime;
	private Double investMoney;
	private Product product;
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	
	@Override
	public String toString() {
		return "ProductInvest [id=" + id + ", pid=" + pid + ", uid=" + uid
				+ ", status=" + status + ", startTime=" + startTime
				+ ", investMoney=" + investMoney + ", product=" + product
				+ ", user=" + user + "]";
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
