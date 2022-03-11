package com.alzoharbank.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alzoharbank.webservice.Dao.TransactionHistoryDao;
import com.alzoharbank.webservice.Exception.HistoryNotFound;
import com.alzoharbank.webservice.model.TransactionHistory;

@RestController
public class TransactionHistoryController {

	@Autowired
	TransactionHistoryDao tranHisDao;

	@GetMapping("/history/{accid}")
	public List<TransactionHistory> getTransactionHistoryById(@PathVariable("accid") int accid) {
		List<TransactionHistory> txHistory = tranHisDao.findTransactionHistoryById(accid);
		if (txHistory != null) {
			return txHistory;
		}
		throw new HistoryNotFound("History Not Found With Given Id = " + accid);
	}
}
