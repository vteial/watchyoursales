package io.ahaitech.harmoney.service;

import io.ahaitech.harmoney.dto.MailDto;

public interface MailService {

	void send(MailDto mailDto);

	void sendAsync(MailDto mailDto);

}
