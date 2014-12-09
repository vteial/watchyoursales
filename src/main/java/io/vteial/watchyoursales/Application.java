package io.vteial.watchyoursales;

import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;
import io.vteial.watchyoursales.web.BootLoaderServlet;

import java.text.DecimalFormat;
import java.util.Properties;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// SecurityInterceptor si = new SecurityInterceptor();
		// registry.addInterceptor(si).addPathPatterns("/**");
	}

	@Bean
	public ServletRegistrationBean h2Servlet() {
		ServletRegistrationBean servletBean = new ServletRegistrationBean();

		servletBean.addUrlMappings("/_admin/bootLoader");
		servletBean.setServlet(new BootLoaderServlet());
		servletBean.setLoadOnStartup(1);

		return servletBean;
	}

	@Bean
	Properties javaMailProperties() {
		Properties props = new Properties();

		props.setProperty("mail.smtp.timeout", "3000");
		props.setProperty("mail.smtp.connectiontimeout", "3000");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");

		return props;
	}

	@Bean
	DateTimeFormatter dateTimeFormatterAndParser() {
		return DateTimeFormat.forPattern("dd-MM-yyyy");
	}

	@Bean
	DecimalFormat amountFormatterAndParser() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		return df;
	}

	@Bean
	DecimalFormat rateFormatterAndParser() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(5);
		return df;
	}

	@Bean
	public ObjectMapper jsonObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return objectMapper;
	}

	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	@Bean
	SimpleTemplateEngine simpleTemplateEngine() {
		SimpleTemplateEngine ste = new SimpleTemplateEngine();
		return ste;
	}

	@Bean
	Template displayTemplate() throws Exception {
		Template tmpl = null;

		ClassPathResource cpr = new ClassPathResource("templates/display.html");
		SimpleTemplateEngine ste = this.simpleTemplateEngine();
		tmpl = ste.createTemplate(cpr.getFile());

		return tmpl;
	}

}
