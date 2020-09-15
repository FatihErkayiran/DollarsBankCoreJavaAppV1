package com.dollarsbank.model;

import java.io.Serializable;

public class Customer implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5594036399866436312L;
	private String name;
	private String address;
	private String contactNumber;
	private int userId;
	private String password;
	private int deposit;
	
	public Customer() {
		this("N/A", "N/A", "N/A", 0, "N/A",0);
	}

	public Customer(String name, String address, String contactNumber, int userId, String password,int deposit) {
		super();
		this.name = name;
		this.address = address;
		this.contactNumber = contactNumber;
		this.userId = userId;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	
	
	
	

}
