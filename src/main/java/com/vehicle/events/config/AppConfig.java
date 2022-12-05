package com.vehicle.events.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class AppConfig {

    public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer prop = new PropertySourcesPlaceholderConfigurer();
        prop.setLocation(new ClassPathResource("application.properties"));
        prop.setIgnoreUnresolvablePlaceholders(true);
        return prop;
    }


}
