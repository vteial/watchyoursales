-javaagent:temp/springloaded-1.2.1.RELEASE.jar -noverify

java -jar -Dspring.profiles.active=dev build\libs\


	// @Value("${spring.datasource.v2.driverClassName}")
	// private String databaseDriverClassName;
	//
	// @Value("${spring.datasource.v2.url}")
	// private String datasourceUrl;
	//
	// @Value("${spring.datasource.v2.username}")
	// private String databaseUsername;
	//
	// @Value("${spring.datasource.v2.password}")
	// private String databasePassword;
	//
	// @Bean
	// public DataSource dataSourceV2() throws ClassNotFoundException {
	// SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
	// Class driverClass = Class.forName(this.databaseDriverClassName);
	// dataSource.setDriverClass(driverClass);
	// dataSource.setUrl(this.datasourceUrl);
	// dataSource.setUsername(this.databaseUsername);
	// dataSource.setPassword(this.databasePassword);
	//
	// return dataSource;
	// }
