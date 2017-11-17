package com.altmedia.billboard.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class UserInfo {
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phone;
	private String additonalInfo;

	@DynamoDBAttribute(attributeName = "FirstName")
	public String getFirstName() {
		return firstName;
	}

	@DynamoDBAttribute(attributeName = "LastName")
	public String getLastName() {
		return lastName;
	}

	@DynamoDBAttribute(attributeName = "EmailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	@DynamoDBAttribute(attributeName = "Phone")
	public String getPhone() {
		return phone;
	}

	@DynamoDBAttribute(attributeName = "AdditonalInfo")
	public String getAdditonalInfo() {
		return additonalInfo;
	}

	public UserInfo setfName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserInfo setlName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserInfo setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
		return this;
	}

	public UserInfo setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public UserInfo setAdditonalInfo(String additonalInfo) {
		this.additonalInfo = additonalInfo;
		return this;
	}

}
