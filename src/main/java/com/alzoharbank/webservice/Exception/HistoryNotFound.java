package com.alzoharbank.webservice.Exception;

public class HistoryNotFound extends RuntimeException{

	private static final long serialVersionUID = 1l;

	public HistoryNotFound(String message) {
		super(message);
	}
}
