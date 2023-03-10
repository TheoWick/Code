package it.myproject.web;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import it.myproject.web.dao.UserDao;
import it.myproject.web.dao.impl.UserDaoImpl;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "it.myproject.web.controller")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "it.myproject.web.repository", entityManagerFactoryRef = "emf")
public class MyShopConfig {

	@Bean
	public FreeMarkerViewResolver configureResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		
		return resolver;
	}
	
	@Bean
	public FreeMarkerConfigurer configureFreeMarker() {
		FreeMarkerConfigurer config = new FreeMarkerConfigurer();
		config.setTemplateLoaderPath("/WEB-INF/view/");
		
		return config;
	}
	
	@Bean
	public DataSource getDbConnection() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.mariadb.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setUrl("jdbc:mariadb://localhost:3307/corsospring");
		
		return ds;
	}
	
	@Bean(name="emf")
	public LocalContainerEntityManagerFactoryBean getEntityManager() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(getDbConnection());
		factory.setJpaVendorAdapter(adapter);
		factory.setPackagesToScan(getClass().getPackage().getName());
		return factory;
	}
	
	@Bean
	public PlatformTransactionManager getTransactionManager() {
		JpaTransactionManager jtm = new JpaTransactionManager();
		jtm.setEntityManagerFactory(getEntityManager().getObject());
		return jtm;
	}
	
	@Bean
	public UserDao getClienteService() {
		return new UserDaoImpl();
	}
	
}
