package io.vteial.watchyoursales.model.support;

import io.vteial.watchyoursales.model.Branch;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface BranchBuilder extends Builder<Branch> {

	BranchBuilder withId(long id);

	BranchBuilder withName(String name);

	BranchBuilder withLandPhoneNo(String landPhoneNo);

	BranchBuilder withHandPhoneNo(String handPhoneNo);

	BranchBuilder withFaxNo(String faxNo);

	BranchBuilder withEmailId(String emailId);

	BranchBuilder withAddressId(long addressId);

	BranchBuilder withStatus(String status);

	BranchBuilder withAccountId(long accountId);

	BranchBuilder withParentId(long parentId);

	BranchBuilder withCreateTime(Date createTime);

	BranchBuilder withUpdateTime(Date updateTime);

	BranchBuilder withCreateBy(String createBy);

	BranchBuilder withUpdateBy(String updateBy);
}
