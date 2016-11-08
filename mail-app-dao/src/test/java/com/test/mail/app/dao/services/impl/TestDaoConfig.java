package com.test.mail.app.dao.services.impl;

import com.test.mail.app.dao.conf.HibernateConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by tigran on 11/6/16.
 */
@Configuration
@Import(HibernateConfiguration.class)
public class TestDaoConfig extends HibernateConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:howtodoinjava");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }

    protected Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.archive.autodetection", "class,hbm");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }
}
