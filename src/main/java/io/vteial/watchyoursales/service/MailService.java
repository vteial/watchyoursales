package io.vteial.watchyoursales.service;

import io.vteial.watchyoursales.dto.MailDto;

public interface MailService {

	void send(MailDto mailDto);

	void sendAsync(MailDto mailDto);

}
