package com.alzoharbank.webservice.Exception;

public class AccountNotFound extends RuntimeException{

	private static final long serialVersionUID = 1l;

	public AccountNotFound(String message) {
		super(message);
	}
}
