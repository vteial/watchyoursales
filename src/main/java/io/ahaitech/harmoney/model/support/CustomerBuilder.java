package io.ahaitech.harmoney.model.support;

import io.ahaitech.harmoney.model.Customer;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface CustomerBuilder extends Builder<Customer> {

	CustomerBuilder withId(long id);

	CustomerBuilder withIdentityCardNumber(String identityCardNumber);

	CustomerBuilder withIcFrontBlobDataId(long icFrontBlobDataId);

	CustomerBuilder withIcBackBlobDataId(long icBackBlobDataId);

	CustomerBuilder withFirstName(String firstName);

	CustomerBuilder withLastName(String lastName);

	CustomerBuilder withAliasName(String aliasName);

	CustomerBuilder withGender(String gender);

	CustomerBuilder withDob(Date dob);

	CustomerBuilder withAddressId(long addressId);

	CustomerBuilder withPassportExpiry(Date passportExpiry);

	CustomerBuilder withPlaceOfIssue(String placeOfIssue);

	CustomerBuilder withMaritalStatus(String maritalStatus);

	CustomerBuilder withOccupation(String occupation);

	CustomerBuilder withStatus(String status);

	CustomerBuilder withAccountId(long accountId);

	CustomerBuilder withBranchId(long branchId);

	CustomerBuilder withOldPassportReference(String oldPassportReference);

	CustomerBuilder withPassportFrontBlobDataId(long passportFrontBlobDataId);

	CustomerBuilder withPassportBackBlobDataId(long passportBackBlobDataId);

	CustomerBuilder withIssuingCountry(String issuingCountry);

	CustomerBuilder withCreateTime(Date createTime);

	CustomerBuilder withUpdateTime(Date updateTime);

	CustomerBuilder withCreateBy(String createBy);

	CustomerBuilder withUpdateBy(String updateBy);
}
