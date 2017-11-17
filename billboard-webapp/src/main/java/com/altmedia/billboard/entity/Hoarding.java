package com.altmedia.billboard.entity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

public class Hoarding extends BillBoard {
	private Address address;
	private int size;
	private List<String> imageUrls;

	@DynamoDBAttribute(attributeName = "Address")
	public Address getAddress() {
		return address;
	}

	@DynamoDBAttribute(attributeName = "Size")
	public int getSize() {
		return size;
	}

	@DynamoDBAttribute(attributeName = "ImageUrls")
	public List<String> getImageUrls() {
		return imageUrls;
	}

	public Hoarding setAddress(Address address) {
		this.address = address;
		return this;
	}

	public Hoarding setSize(int size) {
		this.size = size;
		return this;
	}

	public Hoarding setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
		return this;
	}

}
