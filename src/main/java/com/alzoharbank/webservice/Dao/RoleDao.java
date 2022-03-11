package com.alzoharbank.webservice.Dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alzoharbank.webservice.model.Role;

@Repository
public class RoleDao {

	@Autowired
	JdbcTemplate template;

	class RoleMapper implements RowMapper<Role> {

		@Override
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role = new Role();
			role.setId(rs.getInt(1));
			role.setName(rs.getString(2));
			role.setCreatedAt(rs.getDate(3));
			return role;
		}
	}

	public List<Role> findAll() {
		List<Role> roles = new LinkedList<Role>();
		roles = template.query("select * from roles", new RoleMapper());
		return roles;
	}

	public Role findById(int id) {
		return template.queryForObject("select * from roles where id = ? ", new RoleMapper(), id);
	}

	public Role findRoleByname(String name) {
		return template.queryForObject("select * from roles where name = ? ", new RoleMapper(), name);
	}

	public int insert(Role role) {
		return template.update("insert into roles (id, name ) " + "values(?, ? )",
				new Object[] { role.getId(), role.getName() });
	}

	public int update(Role role) {
		return template.update("update roles " + "set name = ? " + " where id = ? ",
				new Object[] { role.getName(), role.getId() });
	}

	public int delete(int id) {
		return template.update("delete from roles where id=?", id);
	}

}
