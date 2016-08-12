package com.chinosoft.p2pinvest.bean;

import java.io.Serializable;
import java.util.List;

public class BorrowPlatform implements Serializable{
	
	private Integer id;
	private String name;
	private String title;
	private Integer peopleNum;
	private Double annualRate;
	private List<Borrower> borrower;
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "BorrowPlatform [id=" + id + ", name=" + name + ", title="
				+ title + ", peopleNum=" + peopleNum + ", annualRate="
				+ annualRate + ", borrower=" + borrower + "]";
	}
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
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public Double getAnnualRate() {
		return annualRate;
	}
	public void setAnnualRate(Double annualRate) {
		this.annualRate = annualRate;
	}
	public List<Borrower> getBorrower() {
		return borrower;
	}
	public void setBorrower(List<Borrower> borrower) {
		this.borrower = borrower;
	}

}
