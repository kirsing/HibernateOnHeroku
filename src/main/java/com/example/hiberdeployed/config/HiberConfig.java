package com.example.hiberdeployed.config;


import org.hibernate.SessionFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HiberConfig {


    //@Bean // ставится над методом который выводит класс, объект этого класса создаётся и хранится в Spring Application
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://ec2-52-212-228-71.eu-west-1.compute.amazonaws.com:5432/d51iv7kf4108o2");
        dataSourceBuilder.username("nfhqdhdqmxwgtg");
        dataSourceBuilder.password("e99090e03cb20241f227b674b482541f419dc97e2dbdc0e9d3f0761e00683930");
        return dataSourceBuilder.build();

        //postgres://nfhqdhdqmxwgtg:e99090e03cb20241f227b674b482541f419dc97e2dbdc0e9d3f0761e00683930@ec2-52-212-228-71.eu-west-1.compute.amazonaws.com:5432/d51iv7kf4108o2
    }
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }

    //        @Bean
//        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//            LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean() ;
//            emfb.setDataSource(getDataSource());
//            emfb.setPackagesToScan("com.example.car.model");
//            emfb.setJpaProperties(additionalProperties());
//            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter() ;
//            emfb.setJpaVendorAdapter(vendorAdapter);
//            return emfb ;
//        }
    @Bean
    public SessionFactory getSessionFactory() throws IOException {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan("com.example.hiberdeployed.model");
        factoryBean.setHibernateProperties(additionalProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory factory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(factory);
        return transactionManager;
    }

}
