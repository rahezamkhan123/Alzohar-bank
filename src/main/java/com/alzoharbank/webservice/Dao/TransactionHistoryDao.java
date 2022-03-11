package com.alzoharbank.webservice.Dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alzoharbank.webservice.model.TransactionHistory;

@Repository
public class TransactionHistoryDao {

	@Autowired
	JdbcTemplate template;

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

	public List<TransactionHistory> findTransactionHistoryById(int accid) {
		return template.query("select * from transactionhistorys where accid=?", new TransactionHistoryMapper(), accid);
	}
}