package com.alzoharbank.webservice.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alzoharbank.webservice.DTO.StatementDto;
import com.alzoharbank.webservice.DTO.TransactionDto;
import com.alzoharbank.webservice.Dao.TransactionDao.TransactionMapper;
import com.alzoharbank.webservice.Exception.StatementNotFound;

@Repository
public class StatementDao {

	@Autowired
	JdbcTemplate template;

	class StatementMapper implements RowMapper<StatementDto> {
		@Override
		public StatementDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			StatementDto state = new StatementDto();
			state.setId(rs.getInt(1));
			state.setAccid(rs.getInt(2));
			state.setUsername(rs.getString(3));
			state.setEmail(rs.getString(4));
			state.setPassword(rs.getString(5));
			state.setCardPin(rs.getLong(6));
			state.setCardNo(rs.getLong(7));
			state.setBalance(rs.getDouble(8));
			state.setOldBalance(rs.getDouble(9));
			state.setNewBalance(rs.getDouble(10));
			state.setAmount(rs.getDouble(11));
			state.setTransactionType(rs.getString(12));
			state.setCreatedAt(rs.getDate(13));

			return state;
		}
	}

	public StatementDto statementById(int accid) {
		StatementDto stdto = new StatementDto();
		stdto = template.queryForObject(
				"select accounts.id,accounts.accid,accounts.username,accounts.email,accounts.password,accounts.cardPin,accounts.cardNo,accounts.balance,accounts.oldBalance,accounts.newBalance,accounts.amount,accounts.transactionType,accounts.createdAt from accounts INNER JOIN transactionhistorys as trans ON accounts.accid=trans.accid",
				new StatementMapper());
		if (stdto == null) {
			throw new StatementNotFound("Statement Not Found!");

		}
		return stdto;
	}
}
