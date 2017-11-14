package com.altmedia.billboard.entity;

public class User {
	private String fName;
	private String lName;
	private String emailAddress;
	private String phone;
	private String additonalInfo;

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPhone() {
		return phone;
	}

	public String getAdditonalInfo() {
		return additonalInfo;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAdditonalInfo(String additonalInfo) {
		this.additonalInfo = additonalInfo;
	}

}
