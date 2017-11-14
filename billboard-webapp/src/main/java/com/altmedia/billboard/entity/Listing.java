package com.altmedia.billboard.entity;

import java.util.Date;

public abstract class Listing {
	private String hoardingId;
	private String specialInstructions;
	private int priority;
	private User createdBy;
	private String modifiedById;
	private Date createdDate;
	private Date modifiedDate;

	public String getHoardingId() {
		return hoardingId;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setHoardingId(String hoardingId) {
		this.hoardingId = hoardingId;
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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
