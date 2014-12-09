package io.ahaitech.harmoney.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ITEM")
@Data
public class Item extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static final String ID_KEY = "itemId";

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "base_unit")
	private double baseUnit;

	@Column(name = "denominator")
	private double denominator;

	@Column(name = "buy_rate")
	private double buyRate;

	@Column(name = "buy_percent")
	private double buyPercent;

	@Column(name = "sell_rate")
	private double sellRate;

	@Column(name = "sell_percent")
	private double sellPercent;

	@Column(name = "hand_stock_average")
	private double handStockAverage;

	@Column(name = "virtualStockAverage")
	private double virtualStockAverage;

	@Column(name = "available_stock_average")
	private double availableStockAverage;

	@Column(name = "branch_id")
	private long branchId;

	// @OneToOne
	private transient Branch branch;

	@Column(name = "create_time")
	protected Date createTime;

	@Column(name = "update_time")
	protected Date updateTime;

	@Column(name = "create_by")
	protected String createBy;

	@Column(name = "update_by")
	protected String updateBy;

	// Persistance operations
	@PreUpdate
	public void preUpdate() {
		this.updateTime = new Date();
	}

	@PrePersist
	public void prePersist() {
		Date now = new Date();
		this.createTime = now;
		this.updateTime = now;
	}

	// Domain Operations
	public double getBuyPercentageRate() {
		double value = this.buyRate * (this.buyPercent / 100);
		value = this.buyRate + value;
		return value;
	}

	public double getSellPercentageRate() {
		double value = this.sellRate * (this.sellPercent / 100);
		value = this.sellRate - value;
		return value;
	}

	public boolean isRateIsAllowableForBuy(double rate) {
		double bpr = this.getBuyPercentageRate();
		return rate <= bpr;
	}

	public boolean isRateIsAllowableForSell(double rate) {
		double spr = this.getSellPercentageRate();
		return rate >= spr;
	}

	public void computeHandStockAverage(Stock stock, double unit, double rate) {
		// System.out.println("unit = " + unit);
		// System.out.println("rate = " + rate);
		// System.out.println("hStockTotal = " + stock.getHandStockTotal());
		double value1 = stock.getHandStockTotal()
				* (this.handStockAverage / this.baseUnit);
		// System.out.println("value1 = " + value1);
		double value2 = unit * (rate / this.baseUnit);
		// System.out.println("value2 = " + value2);
		double value3 = value1 + value2;
		// System.out.println("value3 = " + value3);
		double value4 = stock.getHandStockTotal() + unit;
		// System.out.println("value4 = " + value4);
		double value5 = (value3 / value4) * this.baseUnit;
		// System.out.println("value5 = " + value5);
		this.handStockAverage = value5;
	}

	public void revertHandStockAverage(Stock stock, double unit, double rate) {
		double hs = stock.getHandStockTotal() - unit;
		if (hs > 0) {
			// System.out.println("unit = " + unit);
			// System.out.println("rate = " + rate);
			// System.out.println("hStockTotal = " + stock.getHandStockTotal());
			double value1 = stock.getHandStockTotal()
					* (this.handStockAverage / this.baseUnit);
			// System.out.println("value1 = " + value1);
			double value2 = unit * (rate / this.baseUnit);
			// System.out.println("value2 = " + value2);
			double value3 = value1 - value2;
			// System.out.println("value3 = " + value3);
			double value4 = stock.getHandStockTotal() - unit;
			// System.out.println("value4 = " + value4);
			double value5 = (value3 / value4) * this.baseUnit;
			// System.out.println("value5 = " + value5);
			this.handStockAverage = value5;
		}
	}

	public void computeVirtualStockAverage(Stock stock, double unit, double rate) {
		// System.out.println("unit = " + unit);
		// System.out.println("rate = " + rate);
		// System.out.println("vStockBuy = stock.getVirtualStockBuy());
		double value1 = stock.getVirtualStockBuy()
				* (this.virtualStockAverage / this.baseUnit);
		// System.out.println("value1 = " + value1);
		double value2 = unit * (rate / this.baseUnit);
		// System.out.println("value2 = " + value3);
		double value3 = value1 + value2;
		// System.out.println("value3 = " + value3);
		double value4 = stock.getVirtualStockBuy() + unit;
		// System.out.println("value4 = " + value4);
		double value5 = (value3 / value4) * this.baseUnit;
		// System.out.println("value5 = " + value5);
		this.virtualStockAverage = value5;
	}

	public void revertVirtualStockAverage(Stock stock, double unit, double rate) {
		double vsb = stock.getVirtualStockBuy() - unit;
		if (vsb != 0) {
			// System.out.println("unit = " + unit);
			// System.out.println("rate = " + rate);
			// System.out.println("vStockBuy = stock.getVirtualStockBuy());
			double value1 = stock.getVirtualStockBuy()
					* (this.virtualStockAverage / this.baseUnit);
			// System.out.println("value1 = " + value1);
			double value2 = unit * (rate / this.baseUnit);
			// System.out.println("value2 = " + value2);
			double value3 = value1 - value2;
			// System.out.println("value3 = " + value3);
			double value4 = stock.getVirtualStockBuy() - unit;
			// System.out.println("value4 = " + value4);
			double value5 = (value3 / value4) * this.baseUnit;
			// System.out.println("value5 = " + value5);
			this.virtualStockAverage = value5;
		}
	}

	public void computeAvailableStockAverage(Stock stock, double sellRate) {
		double value1 = stock.getVirtualStockBuy()
				* (this.virtualStockAverage / this.baseUnit);
		// System.out.println("value1 = " + value1);
		double value2 = stock.getHandStockTotal()
				* (this.handStockAverage / this.baseUnit);
		// System.out.println("value2 = " + value2);
		double value3 = value1 + value2;
		// System.out.println("value3 = " + value3);
		double value4 = stock.getVirtualStockBuy() + stock.getHandStockTotal();
		// System.out.println("value4 = " + value4);
		double value5 = (value3 / value4) * this.baseUnit;
		// System.out.println("value5 = " + value5);
		this.availableStockAverage = Double.isNaN(value5) ? 0 : value5;
	}
}
