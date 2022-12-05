package com.vehicle.events.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.vehicle.events")
public class FleetEventsApp {
    public static void main(String... args) {
        SpringApplication.run(FleetEventsApp.class, args);
    }
}
