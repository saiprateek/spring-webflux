package com.vehicle.events.service;

import com.vehicle.events.domains.Events;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface EventsService {
    Mono<Events> findEventById(String eventId);

    Flux<Events> findAll();

    Mono<Void> delete(String id);

    Disposable createEvents(Events events);
}
