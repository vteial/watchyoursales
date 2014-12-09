package io.vteial.watchyoursales;

import io.vteial.watchyoursales.service.SessionService;
import io.vteial.watchyoursales.service.impl.DefaultSessionServicePrd;

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
