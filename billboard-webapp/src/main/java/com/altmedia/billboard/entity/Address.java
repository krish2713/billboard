package com.altmedia.billboard.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Address {
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;

	@DynamoDBAttribute(attributeName = "AddressLine1")
	public String getAddressLine1() {
		return addressLine1;
	}

	@DynamoDBAttribute(attributeName = "AddressLine2")
	public String getAddressLine2() {
		return addressLine2;
	}

	@DynamoDBAttribute(attributeName = "City")
	public String getCity() {
		return city;
	}

	@DynamoDBAttribute(attributeName = "State")
	public String getState() {
		return state;
	}

	@DynamoDBAttribute(attributeName = "Country")
	public String getCountry() {
		return country;
	}

	public Address setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
		return this;
	}

	public Address setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
		return this;
	}

	public Address setCity(String city) {
		this.city = city;
		return this;
	}

	public Address setState(String state) {
		this.state = state;
		return this;
	}

	public Address setCountry(String country) {
		this.country = country;
		return this;
	}

}
