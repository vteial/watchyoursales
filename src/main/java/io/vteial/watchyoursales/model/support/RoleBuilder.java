package io.vteial.watchyoursales.model.support;

import io.vteial.watchyoursales.model.Role;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface RoleBuilder extends Builder<Role> {

	RoleBuilder withId(String id);

	RoleBuilder withName(String name);
	
	RoleBuilder withCreateTime(Date createTime);

	RoleBuilder withUpdateTime(Date updateTime);

	RoleBuilder withCreateBy(String createBy);

	RoleBuilder withUpdateBy(String updateBy);
}
