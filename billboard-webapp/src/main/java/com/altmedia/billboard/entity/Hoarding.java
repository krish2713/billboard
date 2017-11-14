package com.altmedia.billboard.entity;

import java.util.List;

public class Hoarding extends BillBoard{
	private Address address;
	private int size;
	private List<String> imageUrls;

	public Address getAddress() {
		return address;
	}

	public int getSize() {
		return size;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

}
