package io.ahaitech.harmoney;

import io.ahaitech.harmoney.service.SessionService;
import io.ahaitech.harmoney.service.impl.DefaultSessionServicePrd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!dev")
public class ApplicationPrd {

	@Bean
	SessionService sessionService() {
		return new DefaultSessionServicePrd();
	}

}
