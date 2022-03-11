package com.alzoharbank.webservice.model;

import java.util.Date;

public class Statement {

	private int id;
	private int accid;
	private String username;
	private String email;
	private String password;
	private long cardPin;
	private long cardNo;
	private double balance;
	private double oldBalance;
	private double newBalance;
	private double amount;
	private String transactionType;
	private Date createdAt;

	public Statement(int id, int accid, String username, String email, String password, long cardPin, long cardNo,
			double balance, double oldBalance, double newBalance, double amount, String transactionType,
			Date createdAt) {
		super();
		this.id = id;
		this.accid = accid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.cardPin = cardPin;
		this.cardNo = cardNo;
		this.balance = balance;
		this.oldBalance = oldBalance;
		this.newBalance = newBalance;
		this.amount = amount;
		this.transactionType = transactionType;
		this.createdAt = createdAt;
	}

	public Statement() {
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

	public void setAccid(int accid) {
		this.accid = accid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getCardPin() {
		return cardPin;
	}

	public void setCardPin(long cardPin) {
		this.cardPin = cardPin;
	}

	public long getCardNo() {
		return cardNo;
	}

	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
