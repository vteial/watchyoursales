package io.ahaitech.harmoney.v2.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerHelper implements RowMapper<Customer>,
		RowCallbackHandler {

	@Resource
	JdbcTemplate jdbcTemplateV2;
	
	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer model = new Customer();

		model.setId(rs.getLong("id"));

		return model;
	}

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		Customer customer = this.mapRow(rs, 0);

		System.out.println(customer);
	}
}
