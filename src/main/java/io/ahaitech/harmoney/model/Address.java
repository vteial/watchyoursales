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
@Table(name = "ADDRESS")
@Data
public class Address extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static final String ID_KEY = "addressId";

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "address")
	private String address;

	@Column(name = "city_or_town")
	private String cityOrTown;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "state_code")
	private String stateCode;

	@Column(name = "country_code")
	private String countryCode;

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
