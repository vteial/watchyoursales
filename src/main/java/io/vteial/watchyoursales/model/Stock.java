package io.vteial.watchyoursales.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "STOCK")
@Data
public class Stock extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static final String ID_KEY = "stockId";

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "hand_stock")
	private double handStock;

	@Column(name = "hand_stock_move_teller")
	private double handStockMoveTeller;

	@Column(name = "hand_stock_move_branch")
	private double handStockMoveBranch;

	private transient double handStockTotal;

	@Column(name = "virtual_stock_buy")
	private double virtualStockBuy;

	@Column(name = "virtual_stock_sell")
	private double virtualStockSell;

	@Column(name = "availableStock")
	private double availableStock;

	@Column(name = "itemId")
	private long itemId;

	// @OneToOne
	private transient Item item;

	@Column(name = "user_id")
	private String userId;

	// @OneToOne
	private transient User user;

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

	// Persistance Operations
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
	public boolean hasSufficientHandStock(double amount) {
		return amount <= this.handStock;
	}

	public void withdrawHandStock(double amount) {
		this.handStock -= amount;
	}

	public void depositHandStock(double amount) {
		this.handStock += amount;
	}

	public double getVirtualStock() {
		return this.virtualStockBuy - this.virtualStockSell;
	}

	public boolean hasSufficientVirtualStockBuy(double amount) {
		return amount <= this.virtualStockBuy;
	}

	public boolean hasSufficientVirtualStockSell(double amount) {
		return amount <= this.virtualStockSell;
	}

	public void withdrawVirtualStockBuy(double amount) {
		this.virtualStockBuy -= amount;
	}

	public void depositVirtualStockBuy(double amount) {
		this.virtualStockBuy += amount;
	}

	public void withdrawVirtualStockSell(double amount) {
		this.virtualStockSell -= amount;
	}

	public void depositVirtualStockSell(double amount) {
		this.virtualStockSell += amount;
	}

	public void computeAvailableStock() {
		this.availableStock = this.getVirtualStock() + this.getHandStockTotal();
	}

}
