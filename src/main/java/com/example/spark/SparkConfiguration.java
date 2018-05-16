package com.example.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.hive.thriftserver.HiveThriftServer2;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Properties;

@Configuration
public class SparkConfiguration {

    private SparkSession session;

    @Resource(name = "sparkProperties")
    private Properties properties;


    @PostConstruct
    public void init() {
        SparkConf sparkConf = new SparkConf(true);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            sparkConf.set(key, value);
        }

        SparkSession.Builder builder = SparkSession
                .builder()
                .config(sparkConf)
                .enableHiveSupport();

        session = builder.getOrCreate();
        HiveThriftServer2.startWithContext(session.sqlContext());
    }

    @PreDestroy
    public void destroy() {
        if (session != null) {
            session.close();
        }
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public synchronized SparkSession session() {
        return session.newSession();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public synchronized SparkSqlSession sqlSession() {
        SparkSession sparkSession = session.newSession();
        return new SparkSqlSession(sparkSession);
    }
}
