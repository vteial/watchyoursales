package io.ahaitech.harmoney.dto;

import lombok.Data;

@Data
public class MailDto {

	String fromAddress;

	String toAddress;

	String subject;

	String body;
}
