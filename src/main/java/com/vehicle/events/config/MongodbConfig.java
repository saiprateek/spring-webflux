package com.vehicle.events.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.vehicle.events")
public class MongodbConfig extends AbstractReactiveMongoConfiguration {
    @Value("${dbname}")
    private String dbName;

    @Value("${port}")
    private String port;

    public MongoClient getMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        System.out.println("mongodb clinet............:" + reactiveMongoClient());
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

}
