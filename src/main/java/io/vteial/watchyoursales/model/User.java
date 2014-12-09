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
@Table(name = "USERS")
@Data
public class User extends AbstractModel {

	private static final long serialVersionUID = 1L;

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

	// Domain
	public static String makeVirtualUserIdForBranch(long branchId) {
		return "bvu-" + branchId;
	}

	public boolean isVirtualUser() {
		String userId = "bvu-" + this.branchId;
		return this.id.equals(userId);
	}

	public void setVirtualUser(boolean flag) {

	}

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "password")
	private String password;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "status")
	private String status;

	@Column(name = "employee_id")
	private long employeeId;

	// @OneToOne
	private transient Employee employee;

	@Column(name = "role_id")
	private String roleId;

	// @OneToOne
	private transient Role role;

	@Column(name = "branch_id")
	private long branchId;

	// @OneToOne
	private transient Branch branch;

	@Column(name = "cash_account_id")
	private long cashAccountId;

	// @OneToOne
	private transient Account cashAccount;

	@Column(name = "profit_account_id")
	private long profitAccountId;

	// @OneToOne
	private transient Account profitAccount;

	private transient String newPassword;

	@Column(name = "create_time")
	protected Date createTime;

	@Column(name = "update_time")
	protected Date updateTime;

	@Column(name = "create_by")
	protected String createBy;

	@Column(name = "update_by")
	protected String updateBy;

}