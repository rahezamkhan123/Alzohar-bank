package com.alzoharbank.webservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alzoharbank.webservice.DTO.AccountDto;
import com.alzoharbank.webservice.DTO.TransactionDto;
import com.alzoharbank.webservice.Dao.AccountDao;
import com.alzoharbank.webservice.Dao.TransactionDao;
import com.alzoharbank.webservice.Exception.AccountNotFound;
import com.alzoharbank.webservice.model.Account;

@RestController
public class AccountController {

	@Autowired
	AccountDao accountDao;

	@Autowired
	TransactionDao transactionDao;

	@GetMapping("/account")
	public Account getAccountByusername(@RequestParam("username") String username) {
		Account account = accountDao.findAccountByusername(username);
		if (account != null) {
			return account;
		}
		throw new AccountNotFound("Account Not Found With Given username = " + username);
	}

	@GetMapping("/accounts")
	public List<Account> getAccounts() {
		List<Account> list = accountDao.findAll();
		if (list.isEmpty()) {
			throw new AccountNotFound("Account list is Empty, Zero Records Found.");
		}
		return list;
	}

	@GetMapping("/accounts/{accid}")
	public Account getAccountByaccId(@PathVariable("accid") int accid) {
		Account account = accountDao.findById(accid);
		if (account != null) {
			return account;
		}
		throw new AccountNotFound("Account Not Found With Given accId = " + accid);
	}

	@PostMapping("/accounts")
	public Map<String, String> addAccounts(@RequestBody Account account) {
		int rowsAffected = accountDao.insert(account);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Account Added Successfully !");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

	@PutMapping("/accounts/{id}")
	public Map<String, String> updateAccountByaccId(@PathVariable("id") int id, @RequestBody Account account) {
		int rowsAffected = accountDao.update(account);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Account Updeted Successfully !");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

	@DeleteMapping("/account/{id}")
	public Map<String, String> deleteAccontByaccId(@PathVariable("id") int id) {
		int rowsAffected = accountDao.delete(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Account Deleted Successfully !");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;
	}

	@GetMapping("/balance/{accid}")
	public AccountDto getBalanceByaccId(@PathVariable("accid") int accid) {
		AccountDto acc = accountDao.showBalanceById(accid);
		if (acc != null) {
			return acc;
		}
		throw new AccountNotFound("Account Not Found With Given Id = " + accid);
	}

	@GetMapping("/accounte")
	public Account showAccountByEmail(@RequestParam("email") String email) {
		Account acc = accountDao.showAccountByemail(email);
		if (acc != null) {
			return acc;
		}
		throw new AccountNotFound("Account Not Found With Given email = " + email);
	}
}
