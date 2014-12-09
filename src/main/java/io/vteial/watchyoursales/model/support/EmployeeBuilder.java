package io.vteial.watchyoursales.model.support;

import io.vteial.watchyoursales.model.Employee;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface EmployeeBuilder extends Builder<Employee> {

	EmployeeBuilder withId(long id);

	EmployeeBuilder withIdentityCardNumber(String identityCardNumber);

	EmployeeBuilder withEmailId(String emailId);

	EmployeeBuilder withFirstName(String firstname);

	EmployeeBuilder withLastName(String lastname);

	EmployeeBuilder withDob(Date dob);

	EmployeeBuilder withGender(String gender);

	EmployeeBuilder withLandPhoneNumber(String landPhoneNumber);

	EmployeeBuilder withHandPhoneNumber(String handPhoneNumber);

	EmployeeBuilder withAddressId(long addressId);

	EmployeeBuilder withStatus(String status);

	EmployeeBuilder withCreateTime(Date createTime);

	EmployeeBuilder withUpdateTime(Date updateTime);

	EmployeeBuilder withCreateBy(String createBy);

	EmployeeBuilder withUpdateBy(String updateBy);
}
