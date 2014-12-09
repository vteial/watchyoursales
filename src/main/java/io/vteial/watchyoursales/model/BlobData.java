package io.vteial.watchyoursales.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "BLOB_DATA")
@Data
@ToString(exclude = "content")
public class BlobData extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static final String ID_KEY = "blobDataId";

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

	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "content")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] content;

	@Column(name = "create_time")
	protected Date createTime;

	@Column(name = "update_time")
	protected Date updateTime;

	@Column(name = "create_by")
	protected String createBy;

	@Column(name = "update_by")
	protected String updateBy;

}
