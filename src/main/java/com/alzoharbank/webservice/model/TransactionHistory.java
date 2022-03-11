package com.alzoharbank.webservice.model;

import java.util.Date;

public class TransactionHistory {

	private int id;
	private int accid;
	private String username;
	private double oldBalance;
	private double newBalance;
	private double amount;
	private String transactionType;
	private Date createdAt;

	public TransactionHistory(int id, int accid, String username, double oldBalance, double newBalance, double amount,
			String transactionType, Date createdAt) {
		super();
		this.id = id;
		this.accid = accid;
		this.username = username;
		this.oldBalance = oldBalance;
		this.newBalance = newBalance;
		this.amount = amount;
		this.transactionType = transactionType;
		this.createdAt = createdAt;
	}

	public TransactionHistory() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccid() {
		return accid;
	}

	public String gettransactionType() {
		return transactionType;
	}

	public void settransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void setAccid(int accid) {
		this.accid = accid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(double oldBalance) {
		this.oldBalance = oldBalance;
	}

	public double getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
