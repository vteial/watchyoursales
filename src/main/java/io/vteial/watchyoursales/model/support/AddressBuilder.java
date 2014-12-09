package io.vteial.watchyoursales.model.support;

import io.vteial.watchyoursales.model.Address;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface AddressBuilder extends Builder<Address> {

	AddressBuilder withId(long id);

	AddressBuilder withAddress(String address);

	AddressBuilder withCityOrTown(String cityOrTown);

	AddressBuilder withPostalCode(String postalCode);

	AddressBuilder withStateCode(String stateCode);

	AddressBuilder withCountryCode(String countryCode);

	AddressBuilder withCreateTime(Date createTime);

	AddressBuilder withUpdateTime(Date updateTime);

	AddressBuilder withCreateBy(String createBy);

	AddressBuilder withUpdateBy(String updateBy);
}
