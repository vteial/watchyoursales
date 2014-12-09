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
@Table(name = "COUNTRY")
@Data
public class Country extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "code")
	private String id;

	@Column(name = "code3")
	private String threeLetterCode;

	@Column(name = "nbr")
	private int numericCode;

	@Column(name = "name")
	private String name;

	@Column(name = "create_time")
	protected Date createTime;

	@Column(name = "update_time")
	protected Date updateTime;

	@Column(name = "create_by")
	protected String createBy;

	@Column(name = "update_by")
	protected String updateBy;

	// Persistance
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

}