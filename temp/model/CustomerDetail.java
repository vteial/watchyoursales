package io.ahaitech.harmoney.v2.model;

import io.ahaitech.harmoney.model.AbstractModel;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
public class CustomerDetail extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static final String ID_KEY = "customerDetailId";

	private long id;

	private byte[] image;

	private byte[] icFront;

	private byte[] icBack;

	private byte[] passport;

	private byte[] formOne;

	private byte[] formTwo;

	private byte[] formThree;

	private byte[] formFour;

	private String status;

	private Date createTime;

	private Date updateTime;

	private String createBy;

	private String updateBy;

}