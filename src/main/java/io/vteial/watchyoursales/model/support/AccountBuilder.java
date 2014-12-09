package io.vteial.watchyoursales.model.support;

import io.vteial.watchyoursales.model.Account;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface AccountBuilder extends Builder<Account> {

	AccountBuilder withId(String id);

	AccountBuilder withName(String name);

	AccountBuilder withType(String type);

	AccountBuilder withIsMinus(String isMinus);

	AccountBuilder withBalance(double balance);

	AccountBuilder withStatus(String status);

	AccountBuilder withBranchId(long branchId);

	AccountBuilder withCreateTime(Date createTime);

	AccountBuilder withUpdateTime(Date updateTime);

	AccountBuilder withCreateBy(String createBy);

	AccountBuilder withUpdateBy(String updateBy);
}
