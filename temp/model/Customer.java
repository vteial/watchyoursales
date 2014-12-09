package io.ahaitech.harmoney.v2.model;

import io.ahaitech.harmoney.model.AbstractModel;

import java.util.Date;

import lombok.Data;

@Data
public class Customer extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public static final String ID_KEY = "customerId";

	private long id;

	private String firstName;

	private String lastName;

	private String identityCardNumber;

	private String address;

	private String addressLine2;

	private String addressLine3;

	private String cityCode;

	private String stateCode;

	private String postalCode;

	private String countryCode;

	private Date dob;

	private String gender;

	private String maritalStatus;

	private String occupation;

	private Date passportExpiry;

	private String placeOfIssue;

	private String issuingCountry;

	private String oldPassportReference;

	private String remarks;

	private String status;

	private long customerDetailId;

	private long accountId;

	private long branchId;

	private Date createTime;

	private Date updateTime;

	private String createBy;

	private String updateBy;

}
