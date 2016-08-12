
package com.chinosoft.p2pinvest.bean;

public class Notice {
	private Integer id;
	private String imageUrl;
	private Integer pid;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", imageUrl=" + imageUrl + ", pid=" + pid
				+ "]";
	}
	

}
