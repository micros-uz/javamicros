package com.makble.springmvcstart.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
** config data source , entity manager here
*/

@Configuration
@EnableJpaRepositories( basePackages = {"com.makble.springmvcstart.repository" })
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.makble.springmvcstart.service", "com.makble.springmvcstart.repository", "com.makble.springmvcstart.controller"})
@ImportResource("classpath:spring-security.xml")
public class BasicConfig {
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("rootadm");

		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(getClass().getPackage().getName());
		factory.setDataSource(dataSource());
		factory.setJpaProperties(jpaProperties());

		return factory;
	}
	
	private Properties jpaProperties() {  
	        Properties properties = new Properties();  
	        
	        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");  // MySQL5InnoDBDialect   MySQLMyISAMDialect
	        properties.put("hibernate.show_sql", "true");  
	        return properties;  
	}  
	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}
	
	
}