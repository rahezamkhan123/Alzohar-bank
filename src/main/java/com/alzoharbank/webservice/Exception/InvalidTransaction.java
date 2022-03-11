package com.alzoharbank.webservice.Exception;

public class InvalidTransaction extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public InvalidTransaction(String message) {
		super(message);
	}
}
