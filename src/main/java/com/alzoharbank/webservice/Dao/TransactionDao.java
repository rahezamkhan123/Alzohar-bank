package com.alzoharbank.webservice.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alzoharbank.webservice.DTO.AccountDto;
import com.alzoharbank.webservice.DTO.TransactionDto;
import com.alzoharbank.webservice.Dao.AccountDao.AccountMapper;
import com.alzoharbank.webservice.Exception.AccountNotFound;
import com.alzoharbank.webservice.Exception.InvalidTransaction;
import com.alzoharbank.webservice.Exception.StatementNotFound;
import com.alzoharbank.webservice.model.Account;
import com.alzoharbank.webservice.model.TransactionHistory;

@Repository
public class TransactionDao {

	@Autowired
	JdbcTemplate template;

	class TransactionMapper implements RowMapper<TransactionDto> {

		@Override
		public TransactionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			TransactionDto txdto = new TransactionDto();
			txdto.setAccid(rs.getInt(1));
			txdto.setBalance(rs.getDouble(2));
			txdto.setAmount(rs.getDouble(3));
			return txdto;
		}

	}

	class AccBalanceMapper implements RowMapper<TransactionDto> {

		@Override
		public TransactionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			TransactionDto txd = new TransactionDto();
			txd.setAccid(rs.getInt(1));
			txd.setBalance(rs.getDouble(2));
			txd.setUsername(rs.getString(3));
			return txd;
		}
	}

	class TransactionHistoryMapper implements RowMapper<TransactionHistory> {

		@Override
		public TransactionHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
			TransactionHistory trh = new TransactionHistory();
			trh.setId(rs.getInt(1));
			trh.setAccid(rs.getInt(2));
			trh.setUsername(rs.getString(3));
			trh.setOldBalance(rs.getDouble(4));
			trh.setNewBalance(rs.getDouble(5));
			trh.setAmount(rs.getDouble(6));
			trh.setCreatedAt(rs.getDate(7));
			trh.settransactionType(rs.getString(8));
			return trh;
		}
	}

	public TransactionDto showBalance(int accid) {
		TransactionDto dto = new TransactionDto();
		dto = template.queryForObject("select accid, balance,username from accounts where accid=?",
				new AccBalanceMapper(), accid);
		if (dto == null) {
			throw new AccountNotFound("Account Not Found!");
		}
		return dto;
	}

//	Deposit Amount
	public Map<String, String> deposit(TransactionDto tx) {
		Map<String, String> response = new HashMap<String, String>();
		Account acc = new Account();
		String transaction = "Deposit";
		if (tx.getAmount() > 0) {
			TransactionDto fetch = showBalance((int) tx.getAccid());
			double newBalance = fetch.getBalance() + tx.getAmount();
			int rowsAffected = template.update(
					"update accounts set balance= balance + " + tx.getAmount() + " where accid=?",
					new Object[] { tx.getAccid() });
			int rowAffected = template.update(
					"insert into transactionhistorys(accid, username, oldBalance, newBalance, amount, createdAt, transactionType )"
							+ "values(?, ?, ?, ?, ?, ?, ?)",
					new Object[] { tx.getAccid(), fetch.getUsername(), fetch.getBalance(), newBalance, tx.getAmount(),
							new Date(), transaction });
			response.put("message", "Amount has been deposited successfully !");
			response.put("rowsAffected", String.valueOf(rowsAffected));
		} else {
			throw new InvalidTransaction("Amount is Invalid !");
		}
		return response;
	}

//	Withdraw Amount
	public Map<String, String> withdrawAmount(TransactionDto tx) {
		Map<String, String> response = new HashMap<String, String>();
		String transaction = "withdraw";
		if (tx.getAmount() > 0) {
			TransactionDto fetch = showBalance((int) tx.getAccid());
			double newBalance = fetch.getBalance() + tx.getAmount();
			int rowsAffected = template.update(
					"update accounts set balance= balance - " + tx.getAmount() + " where accid=?",
					new Object[] { tx.getAccid() });
			int rowAffected = template.update(
					"insert into transactionhistorys(accid, username, oldBalance, newBalance, amount, createdAt, transactionType )"
							+ "values(?, ?, ?, ?, ?, ?, ?)",
					new Object[] { tx.getAccid(), fetch.getUsername(), fetch.getBalance(), newBalance, tx.getAmount(),
							new Date(), transaction });
			response.put("message", "Amount has been withdraw successfully !");
			response.put("rowsAffected", String.valueOf(rowsAffected));
		} else {
			throw new InvalidTransaction("Amount is Invalid for withdraw !");
		}
		return response;
	}

//	public TransactionDto statement() {
//		TransactionDto dto = new TransactionDto();
//		return template.queryForObject("SELECT FROM accounts INNER JOIN transactionhistorys accid",
//				new TransactionMapper());
//	}

	public TransactionDto statementById(int accid) {
		TransactionDto tdto = new TransactionDto();
		tdto = template.queryForObject(
				"select * from accounts INNER JOIN transactionhistorys as trans ON accounts.accid=trans.accid",
				new TransactionMapper());
		if (tdto == null) {
			throw new StatementNotFound("Statement Not Found!");

		}
		return tdto;
	}

}
