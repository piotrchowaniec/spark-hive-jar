package com.example.spark;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class SparkPropertiesConfiguration {

    @Bean("sparkProperties")
    public PropertiesFactoryBean sparkProperties() {
        PropertiesFactoryBean bean = this.basicConfiguration();
        bean.setLocations(new ClassPathResource("spark.properties"),
                new FileSystemResource("config/spark.properties"));
        return bean;
    }

    private PropertiesFactoryBean basicConfiguration() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setIgnoreResourceNotFound(true);
        return bean;
    }

}
