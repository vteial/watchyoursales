package io.ahaitech.harmoney;

import io.ahaitech.harmoney.model.AutoNumber;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EntityScan(basePackageClasses = AutoNumber.class)
@Configuration
@Slf4j
public class RepositoryConfig {

	@Bean
	ClassPathResource jsonCountryCpr() {
		ClassPathResource cpr = new ClassPathResource("data/country.json");
		return cpr;
	}

	@Bean
	ClassPathResource jsonAddressCpr() {
		ClassPathResource cpr = new ClassPathResource("data/address.json");
		return cpr;
	}

	@Bean
	ClassPathResource jsonBranchCpr() {
		ClassPathResource cpr = new ClassPathResource("data/branch.json");
		return cpr;
	}

	@Bean
	ClassPathResource jsonAccountCpr() {
		ClassPathResource cpr = new ClassPathResource("data/account.json");
		return cpr;
	}

	@Bean
	ClassPathResource jsonRoleCpr() {
		ClassPathResource cpr = new ClassPathResource("data/role.json");
		return cpr;
	}

	@Bean
	ClassPathResource jsonUserCpr() {
		ClassPathResource cpr = new ClassPathResource("data/user.json");
		return cpr;
	}

	@Bean
	ClassPathResource jsonCustomerCpr() {
		ClassPathResource cpr = new ClassPathResource("data/customer.json");
		return cpr;
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	DataSource dataSource() {
		DataSource dataSource = DataSourceBuilder.create().build();

		return dataSource;
	}

	JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource());

		return jdbcTemplate;
	}

	// @Bean
	// @ConfigurationProperties(prefix = "spring.datasource.v2")
	// DataSource dataSourceV2() {
	// DataSource dataSource = DataSourceBuilder.create().build();
	//
	// try {
	// dataSource.getConnection().setReadOnly(true);
	// } catch (SQLException e) {
	// log.warn("Unable to set read only for v2 connection", e);
	// }
	//
	// return dataSource;
	// }
	//
	// @Bean
	// JdbcTemplate jdbcTemplateV2() {
	// JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSourceV2());
	//
	// return jdbcTemplate;
	// }

}
