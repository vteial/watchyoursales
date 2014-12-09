package io.ahaitech.harmoney.model.support;

import io.ahaitech.harmoney.model.User;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface UserBuilder extends Builder<User> {

	UserBuilder withId(String id);

	UserBuilder withPassword(String password);

	UserBuilder withEmailId(String emailId);

	UserBuilder withFirstName(String firstname);

	UserBuilder withLastName(String lastname);

	UserBuilder withStatus(String status);

	UserBuilder withEmployeeId(long employeeId);

	UserBuilder withRoleId(String roleId);

	UserBuilder withBranchId(long branchId);

	UserBuilder withCashAccountId(long cashAccountId);

	UserBuilder withProfitAccountId(long profitAccountId);

	UserBuilder withCreateTime(Date createTime);

	UserBuilder withUpdateTime(Date updateTime);

	UserBuilder withCreateBy(String createBy);

	UserBuilder withUpdateBy(String updateBy);
}
