package com.alzoharbank.webservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alzoharbank.webservice.DTO.TransactionDto;
import com.alzoharbank.webservice.Dao.TransactionDao;
import com.alzoharbank.webservice.Exception.AccountNotFound;
import com.alzoharbank.webservice.Exception.StatementNotFound;
import com.alzoharbank.webservice.model.Account;

@RestController
public class TransactionController {

	@Autowired
	TransactionDao transactionDao;

	@GetMapping("/showbalance/{accid}")
	public TransactionDto getBalance(@PathVariable("accid") int accid) {
		TransactionDto acc = transactionDao.showBalance(accid);
		if (acc != null) {
			return acc;
		}
		throw new AccountNotFound("Account Not Found With Given Id = " + accid);
	}

	@PutMapping("/deposit")
	public Map<String, String> deposit(@RequestBody TransactionDto tx) {
		return transactionDao.deposit(tx);
	}

	@PutMapping("/withdraw")
	public Map<String, String> withdrawAmount(@RequestBody TransactionDto tx) {
		return transactionDao.withdrawAmount(tx);
	}

	@GetMapping("/statement/{accid}")
	public TransactionDto statementById(@PathVariable("accid") int accid) {
		TransactionDto trand = transactionDao.statementById(accid);
		if (trand != null) {
			return trand;
		}
		throw new StatementNotFound("Statement Not Found With Given accId");
	}
}
