package io.ahaitech.harmoney.v2.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailHelper implements RowMapper<CustomerDetail> {

	@Override
	public CustomerDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerDetail model = new CustomerDetail();

		model.setId(rs.getLong("id"));

		return model;
	}

}
