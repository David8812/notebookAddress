package com.app.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//eliminar esta clase si queremos especificar la conexión a BD en el fichero application.properties
@Configuration
@EnableJpaRepositories(basePackages = { "com.app" })
@EnableTransactionManagement
public class SetUpConfig {

	@Bean(destroyMethod = "close")
	DataSource dataSource(Environment env) {
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName("org.postgresql.Driver");
		//para aws elasticbeanstalk
		//System.setProperty("SP_DATASOURCE_URL", "jdbc:postgresql://localhost:5432/notebook_address");
		//System.setProperty("SP_DATASOURCE_USERNAME", "postgres");
		//System.setProperty("SP_DATASOURCE_PASSWORD", "postgres");
		
		//dataSourceConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/notebook_address");
		dataSourceConfig.setJdbcUrl(System.getProperty("SP_DATASOURCE_URL"));
		//dataSourceConfig.setUsername("postgres");
		dataSourceConfig.setUsername(System.getProperty("SP_DATASOURCE_USERNAME"));
		//dataSourceConfig.setPassword("postgres");
		dataSourceConfig.setPassword(System.getProperty("SP_DATASOURCE_PASSWORD"));

		return new HikariDataSource(dataSourceConfig);
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.app");
		Properties jpaProperties = new Properties();

		jpaProperties.put("spring.jpa.generate-ddl", true);

		jpaProperties.put("spring.jpa.database-platform", "org.hibernate.dialect.PostgreSQL9Dialect");

		jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

		jpaProperties.put("hibernate.show_sql", false);

		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

		jpaProperties.put("hibernate.format_sql", true);

		jpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", false);

		jpaProperties.put("hibernate.jdbc.lob.non_contextual_creation", true);

		jpaProperties.put("hibernate.hbm2ddl.auto", "update");

		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
