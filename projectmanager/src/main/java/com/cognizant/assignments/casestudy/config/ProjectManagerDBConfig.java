package com.cognizant.assignments.casestudy.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectManagerDBConfig {
	

	@Bean(name = "PM_DatSource")
	@ConfigurationProperties(prefix = "db.pm")
	@Primary
	public DataSource createProjectManagerDataSource() {
		return DataSourceBuilder.create().build();
	}	
		

}
