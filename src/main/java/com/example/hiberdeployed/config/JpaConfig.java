package com.example.hiberdeployed.config;

import org.hibernate.SessionFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

public class JpaConfig {
    public DataSource getDataSource() { //DataSource -Источник данных
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://ec2-34-253-119-24.eu-west-1.compute.amazonaws.com:5432/d9tsb909urijm9");
        dataSourceBuilder.username("roowjqdsrxjusn");
        dataSourceBuilder.password("066df34f14b04aeced2a14ce6539ca0276388ddfeb6d2fcd90d7392b599cba9d");
        return dataSourceBuilder.build();
    }

    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean() ;
        emfb.setDataSource(getDataSource());
        emfb.setPackagesToScan("com.example.hiberdeployed.model");
        emfb.setJpaProperties(additionalProperties());
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter() ;
        emfb.setJpaVendorAdapter(vendorAdapter);
        return emfb ;
    }


    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory factory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(factory);
        return transactionManager;
    }

}
