package com.alzoharbank.webservice.Dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alzoharbank.webservice.DTO.AccountDto;
import com.alzoharbank.webservice.DTO.TransactionDto;
import com.alzoharbank.webservice.Exception.InvalidTransaction;
import com.alzoharbank.webservice.model.Account;
import com.alzoharbank.webservice.model.TransactionHistory;

@Repository
public class AccountDao {

	@Autowired
	JdbcTemplate template;

	class AccountMapper implements RowMapper<Account> {

		@Override
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account account = new Account();
			account.setId(rs.getInt(1));
			account.setAccid(rs.getInt(2));
			account.setUsername(rs.getString(3));
			account.setEmail(rs.getString(4));
			account.setPassword(rs.getString(5));
			account.setCardPin(rs.getLong(6));
			account.setCardNo(rs.getLong(7));
			account.setBalance(rs.getDouble(8));
			account.setCreatedAt(rs.getDate(9));
			return account;
		}
	}

	public List<Account> findAll() {
		List<Account> accounts = new LinkedList<Account>();
		accounts = template.query("select * from accounts", new AccountMapper());
		return accounts;
	}

	public Account findById(int accid) {
		return template.queryForObject("select * from accounts where id =?", new AccountMapper(), accid);
	}

	public Account findAccountByusername(String username) {
		return template.queryForObject("select * from accounts where username = ? ", new AccountMapper(), username);
	}

	public Account showAccountByemail(String email) {
		return template.queryForObject("select * from accounts where email = ? ", new AccountMapper(), email);
	}

	public int insert(Account account) {
		return template.update(
				"insert into accounts (id,accid, username, email, password, cardPin, cardNo, balance) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ? )",
				new Object[] { account.getId(), account.getAccid(), account.getUsername(), account.getEmail(),
						account.getPassword(), account.getCardPin(), account.getCardNo(), account.getBalance() });
	}

	public int update(Account account) {
		return template.update(
				"update accounts " + "set username = ?, email = ?, cardPin = ?, cardNo = ? " + " where id = ? ",
				new Object[] { account.getUsername(), account.getEmail(), account.getCardPin(), account.getCardNo(),
						account.getId() });
	}

	public int delete(int id) {
		return template.update("delete from accounts where id=?", id);
	}

	public AccountDto showBalanceById(long accid) {
		try {
			return template.queryForObject("select accid,balance from accounts where accid = ? ",
					new RowMapper<AccountDto>() {
						@Override
						public AccountDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							AccountDto acc = new AccountDto();
							acc.setAccid(rs.getInt(1));
							acc.setBalance(rs.getDouble(2));
							return acc;
						}
					}, accid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
