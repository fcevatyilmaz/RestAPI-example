package com.furkanyilmaz.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class HibernateConfig {
      
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.driverClassName}")
	private String driver;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	
	
	
	//Bu şekilde database bilgilerimizi beanleştirdik ve bilgilere göre db'e bağlanırız.
	//Bu bilgileri application.properties dosyasından alır.
    @Bean 
    public DataSource getDataSource() 
    {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}