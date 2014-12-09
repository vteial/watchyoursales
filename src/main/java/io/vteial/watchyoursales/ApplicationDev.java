package io.vteial.watchyoursales;

import io.vteial.watchyoursales.service.SessionService;
import io.vteial.watchyoursales.service.impl.DefaultSessionServiceDev;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ApplicationDev {

	@Bean
	SessionService sessionService() {
		return new DefaultSessionServiceDev();
	}

	@Bean
	public ServletRegistrationBean h2Servlet() {
		ServletRegistrationBean servletBean = new ServletRegistrationBean();

		servletBean.addUrlMappings("/_admin/h2/*");
		servletBean.setServlet(new WebServlet());

		return servletBean;
	}

}
