package com.vehicle.events.test;

import com.vehicle.events.app.FleetEventsApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(classes = FleetEventsApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventsIntegrationTest {
    @Autowired
    private ApplicationContext context;

    @Test
    void test_api_with_webclient() {
        WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + "8080").build()
                .get().uri("/events/999")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void test_api_with_app_context() {
        System.out.println(WebTestClient.bindToApplicationContext(context)
                .build()
                .get().uri("/events/999")
                .exchange()
                .expectStatus().isOk());
    }
}
