package io.ahaitech.harmoney.dto;

import lombok.Data;

@Data
public class MessageDto {

	public static MessageDto createWithMessage(String message) {
		MessageDto a = new MessageDto();
		a.message = message;
		return a;
	}

	protected String message;

}
