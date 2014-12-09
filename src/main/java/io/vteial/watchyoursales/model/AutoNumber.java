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
@Table(name = "AUTO_NUMBER")
@Data
public class AutoNumber extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "val", nullable = false)
	private long value;

	@PreUpdate
	public void preUpdate() {
		this.updateTime = new Date();
		this.updateBy = "SYSTEM";
	}

	@PrePersist
	public void prePersist() {
		Date now = new Date();
		this.createTime = now;
		this.updateTime = now;
		this.createBy = "SYSTEM";
		this.updateBy = "SYSTEM";
	}

	@Column(name = "create_time", nullable = false)
	protected Date createTime;

	@Column(name = "update_time", nullable = false)
	protected Date updateTime;

	@Column(name = "create_by", nullable = false)
	protected String createBy;

	@Column(name = "update_by", nullable = false)
	protected String updateBy;
}