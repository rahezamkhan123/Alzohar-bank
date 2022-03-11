package com.alzoharbank.webservice.model;

import java.util.Date;

public class Account {

	private int id;
	private int accid;
	private String username;
	private String email;
	private String password;
	private long cardPin;
	private long cardNo;
	private double balance;
	private Date createdAt;

	public Account(int id, int accid, String username, String email, String password, long cardPin, long cardNo,
			double balance, Date createdAt) {
		super();
		this.id = id;
		this.accid = accid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.cardPin = cardPin;
		this.cardNo = cardNo;
		this.balance = balance;
		this.createdAt = createdAt;
	}

	public Account() {
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
