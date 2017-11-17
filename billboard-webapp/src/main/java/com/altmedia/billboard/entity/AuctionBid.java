package com.altmedia.billboard.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

public class AuctionBid extends Bid {
	private int bidAmount;

	@DynamoDBAttribute(attributeName = "BidAmount")
	public int getBidAmount() {
		return bidAmount;
	}

	public AuctionBid setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
		return this;
	}
}
