package com.altmedia.billboard.entity;

import java.util.Date;

public abstract class Bid {
	private String listingId;
	private String specialInstructions;
	private int priority;
	private UserInfo userInfo;
	private String modifiedById;
	private Date createdDate;
	private Date modifiedDate;

	public String getHoardingId() {
		return listingId;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setHoardingId(String hoardingId) {
		this.listingId = hoardingId;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public String getModifiedById() {
		return modifiedById;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedById(String modifiedById) {
		this.modifiedById = modifiedById;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
