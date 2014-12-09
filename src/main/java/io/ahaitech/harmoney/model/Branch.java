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
@Table(name = "BRANCH")
@Data
public class Branch extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static final String ID_KEY = "branchId";

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "land_phone_no")
	private String landPhoneNo;

	@Column(name = "hand_phone_no")
	private String handPhoneNo;

	@Column(name = "fax_no")
	private String faxNo;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "address_id")
	private long addressId;

	// @OneToOne
	private transient Address address;

	@Column(name = "status")
	private String status;

	@Column(name = "account_id")
	private long accountId;

	// @OneToOne
	private transient Account account;

	@Column(name = "parent_id")
	private long parentId;

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

	@Column(name = "create_time")
	protected Date createTime;

	@Column(name = "update_time")
	protected Date updateTime;

	@Column(name = "create_by")
	protected String createBy;

	@Column(name = "update_by")
	protected String updateBy;
}
