package com.altmedia.billboard.entity;

import java.util.Date;

public abstract class BillBoard {
	private String id;
	private Date from;
	private Date to;
	private String listingType;
	private int minimumPeriod;
	private int pricePerMonth;
	private Date bidStartDate;
	private Date bidEndDate;
	private String createdById;
	private String modifiedById;
	private Date createdDate;
	private Date modifiedDate;

	public String getId() {
		return id;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

	public String getListingType() {
		return listingType;
	}

	public int getMinimumPeriod() {
		return minimumPeriod;
	}

	public int getPricePerMonth() {
		return pricePerMonth;
	}

	public Date getBidStartDate() {
		return bidStartDate;
	}

	public Date getBidEndDate() {
		return bidEndDate;
	}

	public String getCreatedById() {
		return createdById;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public void setListingType(String listingType) {
		this.listingType = listingType;
	}

	public void setMinimumPeriod(int minimumPeriod) {
		this.minimumPeriod = minimumPeriod;
	}

	public void setPricePerMonth(int pricePerMonth) {
		this.pricePerMonth = pricePerMonth;
	}

	public void setBidStartDate(Date bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
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
}
