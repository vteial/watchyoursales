package io.vteial.watchyoursales.model.support;

import io.vteial.watchyoursales.model.Item;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface ItemBuilder extends Builder<Item> {

	ItemBuilder withId(long id);

	ItemBuilder withCode(String code);

	ItemBuilder withName(String name);

	ItemBuilder withBaseunit(double baseUnit);

	ItemBuilder withDenominator(double denominator);

	ItemBuilder withBuyRate(double buyRate);

	ItemBuilder withBuyPercent(double buyPercent);

	ItemBuilder withSellRate(double sellRate);

	ItemBuilder withSellPercent(double sellPercent);

	ItemBuilder withHandStockAverage(double handStockAverage);

	ItemBuilder withVirtualStockAverage(double virtualStockAverage);

	ItemBuilder withAvailableStockAverage(double availableStockAverage);

	ItemBuilder withBranchId(long branchId);

	ItemBuilder withCreateTime(Date createTime);

	ItemBuilder withUpdateTime(Date updateTime);

	ItemBuilder withCreateBy(String createBy);

	ItemBuilder withUpdateBy(String updateBy);
}
