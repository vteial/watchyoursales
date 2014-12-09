package io.vteial.watchyoursales.model.support;

import io.vteial.watchyoursales.model.Stock;

import java.util.Date;

import com.fluentinterface.builder.Builder;

public interface StockBuilder extends Builder<Stock> {

	StockBuilder withId(long id);

	StockBuilder withHandStock(double handStock);

	StockBuilder withHandStockMoveTeller(double handStockMoveTeller);

	StockBuilder withHandStockMoveBranch(double handStockMoveBranch);

	StockBuilder withVirtualStockBuy(double virtualStockBuy);

	StockBuilder withVirtualStockSell(double virtualStockSell);

	StockBuilder withAvailableStock(double availableStock);

	StockBuilder withItemId(long itemId);

	StockBuilder withUserId(String userId);

	StockBuilder withBranchId(long branchId);

	StockBuilder withCreateTime(Date createTime);

	StockBuilder withUpdateTime(Date updateTime);

	StockBuilder withCreateBy(String createBy);

	StockBuilder withUpdateBy(String updateBy);
}
