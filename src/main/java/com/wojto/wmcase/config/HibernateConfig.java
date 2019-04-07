package com.wojto.wmcase.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;


@Configuration
@ComponentScan(basePackages="com.wojto.wmcase")
@PropertySource("classpath:persistence-mysql.properties")
@EnableTransactionManagement
public class HibernateConfig {
	public HibernateConfig() {}
	
	@Autowired
	private Environment env;
	
	@Bean
	@Autowired
	public HibernateTemplate hibernateTemplate() throws Exception {
			return new HibernateTemplate(sessionFactory());
	}
	
	@Bean
	public SessionFactory sessionFactory() throws Exception {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(getDataSource());
		lsfb.setPackagesToScan("com.wojto.wmcase.entity");
		lsfb.setHibernateProperties(hibernateProperties());
		
		try {
			lsfb.afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lsfb.getObject();
	}
	
	@Bean
	public DataSource getDataSource() {
		try {
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass(env.getProperty("hibernate.connection.driver_class"));
			dataSource.setJdbcUrl(env.getProperty("hibernate.connection.url"));
			dataSource.setUser(env.getProperty("hibernate.connection.user"));
			dataSource.setPassword(env.getProperty("hibernate.connection.password"));
			dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
			dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
			dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
			dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
			
			return dataSource;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@Bean
	public HibernateTransactionManager hibTransMan() throws Exception {
		
		return new HibernateTransactionManager(sessionFactory());
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql" , env.getProperty("hibernate.show_sql"));
		return properties;
	}
	
}
