package io.ahaitech.harmoney.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static final String ID_KEY = "customerId";

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

	@Id
	@Column(name = "customer_id")
	private long id;

	@Column(name = "id_no")
	private String identityCardNumber;

	@Column(name = "ic_front_blob_data_id")
	private long icFrontBlobDataId;

	// @OneToOne
	private transient BlobData icFrontBlobData;

	@Column(name = "ic_back_blob_data_id")
	private long icBackBlobDataId;

	// @OneToOne
	private transient BlobData icBackBlobData;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "alias_name", nullable = true)
	private String aliasName;

	@Column(name = "gender")
	private String gender;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	private Date dob;

	@Column(name = "address_id")
	private long addressId;

	// @OneToOne
	private transient Address address;

	@Temporal(TemporalType.DATE)
	@Column(name = "passport_expiry")
	private Date passportExpiry;

	@Column(name = "place_of_issue")
	private String placeOfIssue;

	@Column(name = "issuingCountry")
	private String issuingCountry;

	@Column(name = "old_passport_ref", nullable = true)
	private String oldPassportReference;

	@Column(name = "passport_front_blob_data_id")
	private long passportFrontBlobDataId;

	// @OneToOne
	private transient BlobData passportFrontBlobData;

	@Column(name = "passport_back_blob_data_id")
	private long passportBackBlobDataId;

	// @OneToOne
	private transient BlobData passportBackBlobData;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "occupation")
	private String occupation;

	@Column(name = "status")
	private String status;

	@Column(name = "account_id")
	private long accountId;

	// @OneToOne
	private transient Account account;

	@Column(name = "branch_Id")
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
}
