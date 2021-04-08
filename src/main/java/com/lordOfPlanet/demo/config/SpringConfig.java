package com.lordOfPlanet.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.lordOfPlanet.demo")
@EnableWebMvc
public class SpringConfig  {
    @Value("${spring.datasource.driver-class-name}")
    private  String driverName;
    @Value("${spring.datasource.url}")
    private  String url;
    @Value("${spring.datasource.password}")
    private  String password;
    @Value("${spring.datasource.username}")
    private  String username;
    /*
     * Data source configuration HSQL Data Base
     */

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
