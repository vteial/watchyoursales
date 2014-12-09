package io.ahaitech.harmoney.dto.support;

import io.ahaitech.harmoney.dto.MailDto;

import org.springframework.stereotype.Component;

@Component
public class MailDtoBuilder {

	public MailDto generateFromBody(String body) {
		MailDto mailDto = new MailDto();

		mailDto.setFromAddress("mail-deamon@localhost.com");
		mailDto.setToAddress("vteial@gmail.com");
		mailDto.setSubject("Test Mail");
		mailDto.setBody(body);

		return mailDto;

	}
}
