package com.example.hiberdeployed.config;


import org.hibernate.SessionFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


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
        dataSourceBuilder.url("jdbc:mysql://eu-cdbr-west-02.cleardb.net/heroku_18b24e95552a849?reconnect=true");
        dataSourceBuilder.username("beecf4f9962b5b");
        dataSourceBuilder.password("94f86852");
        return dataSourceBuilder.build();
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
