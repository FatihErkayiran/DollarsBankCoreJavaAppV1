package com.dollarsbank.model;

import java.sql.Date;

public class Account {

	
	private int balance;
	private int moneyTransaction;
	private Date dateOfTransaction;
	private int user_Id;
	
	public Account() {
		this(0, 0, new Date(0000000L), 0);
	}
	
	public Account(int balance, int moneyTransaction, Date dateOfTransaction, int user_Id) {
		super();
		this.balance = balance;
		this.moneyTransaction = moneyTransaction;
		this.dateOfTransaction = dateOfTransaction;
		this.user_Id = user_Id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getMoneyTransaction() {
		return moneyTransaction;
	}

	public void setMoneyTransaction(int moneyTransaction) {
		this.moneyTransaction = moneyTransaction;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", moneyTransaction=" + moneyTransaction + ", dateOfTransaction="
				+ dateOfTransaction + ", user_Id=" + user_Id + "]";
	}
	
	
	
	
	
}
