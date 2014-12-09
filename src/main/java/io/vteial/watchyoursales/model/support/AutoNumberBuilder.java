package io.vteial.watchyoursales.model.support;

import io.vteial.watchyoursales.model.AutoNumber;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface AutoNumberBuilder extends Builder<AutoNumber> {

	AutoNumberBuilder withId(String id);

	AutoNumberBuilder withValue(long value);
	
	AutoNumberBuilder withCreateTime(Date createTime);

	AutoNumberBuilder withUpdateTime(Date updateTime);

	AutoNumberBuilder withCreateBy(String createBy);

	AutoNumberBuilder withUpdateBy(String updateBy);
}
