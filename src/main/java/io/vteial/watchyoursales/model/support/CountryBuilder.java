package io.vteial.watchyoursales.model.support;

import io.vteial.watchyoursales.model.Country;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface CountryBuilder extends Builder<Country> {

	CountryBuilder withId(String id);

	CountryBuilder withThreeLetterCode(String threeLetterCode);

	CountryBuilder withNumericCode(int numericCode);

	CountryBuilder withName(String name);

	CountryBuilder withCreateTime(Date createTime);

	CountryBuilder withUpdateTime(Date updateTime);

	CountryBuilder withCreateBy(String createBy);

	CountryBuilder withUpdateBy(String updateBy);
}
