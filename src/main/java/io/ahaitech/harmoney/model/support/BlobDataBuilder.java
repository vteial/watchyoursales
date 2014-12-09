package io.ahaitech.harmoney.model.support;

import io.ahaitech.harmoney.model.BlobData;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface BlobDataBuilder extends Builder<BlobData> {

	BlobDataBuilder withId(long id);

	BlobDataBuilder withName(String name);

	BlobDataBuilder withType(String type);

	BlobDataBuilder withContent(byte[] content);

	BlobDataBuilder withCreateTime(Date createTime);

	BlobDataBuilder withUpdateTime(Date updateTime);

	BlobDataBuilder withCreateBy(String createBy);

	BlobDataBuilder withUpdateBy(String updateBy);
}
