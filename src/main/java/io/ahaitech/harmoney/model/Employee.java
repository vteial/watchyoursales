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

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static final String ID_KEY = "employeeId";

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
	@Column(name = "id")
	private long id;

	@Column(name = "id_no")
	private String identityCardNumber;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	private Date dob;

	@Column(name = "gender")
	private String gender;

	@Column(name = "land_phone_no")
	private String landPhoneNumber;

	@Column(name = "hand_phone_no")
	private String handPhoneNumber;

	@Column(name = "address_id")
	private long addressId;

	// @OneToOne
	private transient Address address;

	@Column(name = "status")
	private String status;

	@Column(name = "create_time")
	protected Date createTime;

	@Column(name = "update_time")
	protected Date updateTime;

	@Column(name = "create_by")
	protected String createBy;

	@Column(name = "update_by")
	protected String updateBy;

}