package com.altmedia.billboard.entity;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

public class Auction extends Listing {
	private Date startDate;
	private Date endDate;

	@DynamoDBAttribute(attributeName = "StartDate")
	public Date getStartDate() {
		return startDate;
	}

	@DynamoDBAttribute(attributeName = "EndDate")
	public Date getEndDate() {
		return endDate;
	}

	public Auction setStartDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}

	public Auction setEndDate(Date endDate) {
		this.endDate = endDate;
		return this;
	}

}
