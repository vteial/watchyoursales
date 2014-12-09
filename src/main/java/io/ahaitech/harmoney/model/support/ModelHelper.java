package io.ahaitech.harmoney.model.support;

import org.springframework.stereotype.Component;

import com.fluentinterface.ReflectionBuilder;

@Component
public class ModelHelper {

	public AccountBuilder createAccountBuilder() {
		AccountBuilder builder = ReflectionBuilder.implementationFor(
				AccountBuilder.class).create();
		return builder;
	}

	public AddressBuilder createAddressBuilder() {
		AddressBuilder builder = ReflectionBuilder.implementationFor(
				AddressBuilder.class).create();
		return builder;
	}

	public AutoNumberBuilder createAutoNumberBuilder() {
		AutoNumberBuilder builder = ReflectionBuilder.implementationFor(
				AutoNumberBuilder.class).create();
		return builder;
	}

	public BlobDataBuilder createBlobDataBuilder() {
		BlobDataBuilder builder = ReflectionBuilder.implementationFor(
				BlobDataBuilder.class).create();
		return builder;
	}

	public BranchBuilder createBranchBuilder() {
		BranchBuilder builder = ReflectionBuilder.implementationFor(
				BranchBuilder.class).create();
		return builder;
	}

	public CountryBuilder createCountryBuilder() {
		CountryBuilder builder = ReflectionBuilder.implementationFor(
				CountryBuilder.class).create();
		return builder;
	}

	public CustomerBuilder createCustomerBuilder() {
		CustomerBuilder builder = ReflectionBuilder.implementationFor(
				CustomerBuilder.class).create();
		return builder;
	}

	public EmployeeBuilder createEmployeeBuilder() {
		EmployeeBuilder builder = ReflectionBuilder.implementationFor(
				EmployeeBuilder.class).create();
		return builder;
	}

	public ItemBuilder createItemBuilder() {
		ItemBuilder builder = ReflectionBuilder.implementationFor(
				ItemBuilder.class).create();
		return builder;
	}

	public RoleBuilder createRoleBuilder() {
		RoleBuilder builder = ReflectionBuilder.implementationFor(
				RoleBuilder.class).create();
		return builder;
	}

	public StockBuilder createStockBuilder() {
		StockBuilder builder = ReflectionBuilder.implementationFor(
				StockBuilder.class).create();
		return builder;

	}

	public UserBuilder createUserBuilder() {
		UserBuilder builder = ReflectionBuilder.implementationFor(
				UserBuilder.class).create();
		return builder;

	}

}
