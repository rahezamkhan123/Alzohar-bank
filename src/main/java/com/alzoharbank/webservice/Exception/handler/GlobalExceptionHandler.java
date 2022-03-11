package com.alzoharbank.webservice.Exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alzoharbank.webservice.Exception.AccountNotFound;
import com.alzoharbank.webservice.Exception.HistoryNotFound;
import com.alzoharbank.webservice.Exception.RoleNotFound;
import com.alzoharbank.webservice.Exception.StatementNotFound;
import com.alzoharbank.webservice.Exception.InvalidTransaction;

@ControllerAdvice
public class GlobalExceptionHandler {

	ExceptionResponse response;

	@ExceptionHandler(value = AccountNotFound.class)
	public ResponseEntity<ExceptionResponse> accountNotFound(AccountNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = RoleNotFound.class)
	public ResponseEntity<ExceptionResponse> roleNotFound(RoleNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InvalidTransaction.class)
	public ResponseEntity<ExceptionResponse> invalidTransaction(InvalidTransaction exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.BAD_REQUEST.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = HistoryNotFound.class)
	public ResponseEntity<ExceptionResponse> historyNotFound(HistoryNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = StatementNotFound.class)
	public ResponseEntity<ExceptionResponse> statementNotFound(StatementNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}
}
