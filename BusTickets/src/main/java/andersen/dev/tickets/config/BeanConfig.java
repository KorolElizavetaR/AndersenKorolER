package andersen.dev.tickets.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;

import andersen.dev.tickets.aspect.DmlEnableAspect;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@ComponentScan("andersen.dev.tickets")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@RequiredArgsConstructor
@EnableAspectJAutoProxy (proxyTargetClass=true)
public class BeanConfig {
	@Autowired
	private final Environment environment;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource datasrc = new DriverManagerDataSource();
		datasrc.setDriverClassName(environment.getProperty("hibernate.connection.driver_class"));
		datasrc.setUrl(environment.getProperty("hibernate.connection.url"));
		datasrc.setUsername(environment.getProperty("hibernate.connection.username"));
		datasrc.setPassword(environment.getProperty("hibernate.connection.password"));
		return datasrc;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("andersen.dev.tickets.model");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(hibernateProperties());
		return factoryBean;
	}
}
