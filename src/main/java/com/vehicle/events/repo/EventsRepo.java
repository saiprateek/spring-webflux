package com.vehicle.events.repo;

import com.vehicle.events.domains.Events;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface EventsRepo extends ReactiveMongoRepository<Events, String> {
    @Query("{ 'status': ?0 }")
    Flux<Events> findByStatus(final String Status);
}
