package io.ahaitech.harmoney;

import javax.annotation.Resource;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.mail.javamail.JavaMailSender;

public class HealthIndicatorMail extends AbstractHealthIndicator {

	@Resource
	JavaMailSender mailSender;
	
	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		builder.outOfService().build();
	}
}
