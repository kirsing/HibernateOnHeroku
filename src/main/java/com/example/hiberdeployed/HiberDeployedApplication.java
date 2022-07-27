package com.example.hiberdeployed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HiberDeployedApplication  {

    public static void main(String[] args) {
        SpringApplication.run(HiberDeployedApplication.class, args);
    }

}
