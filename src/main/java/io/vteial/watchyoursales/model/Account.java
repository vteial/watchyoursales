package io.vteial.watchyoursales.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

import com.google.common.base.Strings;

@Entity
@Table(name = "ACCOUNTS")
@Data
public class Account extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static final String ID_KEY = "accountId";

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "alias_name")
	private String aliasName;

	@Column(name = "type")
	private String type;

	@Column(name = "is_minus")
	private String isMinus;

	@Column(name = "balance")
	private double balance;

	@Column(name = "status")
	private String status;

	@Column(name = "branch_id", nullable = true)
	private long branchId;

	// @OneToOne(mappedBy = "account")
	private transient Branch branch;

	// Persistence Operations
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
	public boolean hasSufficientBalance(double amount) {

		if (this.isMinus()) {
			return true;
		}

		return this.balance >= amount;
	}

	public void withdraw(double amount) {
		this.balance -= amount;
	}

	public void deposit(double amount) {
		this.balance += amount;
	}

	public boolean isMinus() {
		String s = Strings.nullToEmpty(this.isMinus);
		return s.equalsIgnoreCase("Y");
	}

	@Column(name = "create_time")
	protected Date createTime;

	@Column(name = "update_time")
	protected Date updateTime;

	@Column(name = "create_by")
	protected String createBy;

	@Column(name = "update_by")
	protected String updateBy;

}
