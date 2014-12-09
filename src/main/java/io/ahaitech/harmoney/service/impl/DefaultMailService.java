package io.ahaitech.harmoney.service.impl;

import io.ahaitech.harmoney.dto.MailDto;
import io.ahaitech.harmoney.service.MailService;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultMailService implements MailService {

	@Resource
	Properties javaMailProperties;

	@Resource
	JavaMailSender mailSender;

	@PostConstruct
	void init() {
		JavaMailSenderImpl jmsl = (JavaMailSenderImpl) this.mailSender;
		jmsl.setJavaMailProperties(this.javaMailProperties);
	}

	@ServiceActivator(inputChannel = "mailChannel")
	@Override
	public void send(@Payload MailDto mailDto) {
		log.info("From : {}, To : {}, Subject : {}, Body : {}",
				mailDto.getFromAddress(), mailDto.getToAddress(),
				mailDto.getSubject(), mailDto.getBody());
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom(mailDto.getFromAddress());
			String[] toAddresses = { mailDto.getToAddress() };
			helper.setTo(toAddresses);
			helper.setSubject(mailDto.getSubject());

			helper.setText(mailDto.getBody(), true);

			mailSender.send(mimeMessage);

		} catch (Throwable t) {
			log.error("Unable to send mail...", t);
		}
	}

	@Async
	@Override
	public void sendAsync(MailDto mailDto) {
		log.info("From : {}, To : {}, Subject : {}, Body : {}",
				mailDto.getFromAddress(), mailDto.getToAddress(),
				mailDto.getSubject(), mailDto.getBody());
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom(mailDto.getFromAddress());
			String[] toAddresses = { mailDto.getToAddress() };
			helper.setTo(toAddresses);
			helper.setSubject(mailDto.getSubject());

			helper.setText(mailDto.getBody(), true);

			mailSender.send(mimeMessage);

		} catch (Throwable t) {
			log.error("Unable to send mail...", t);
		}
	}

	String createFromAddress() {
		return "app-demaon@localhost.com";
	}

}
