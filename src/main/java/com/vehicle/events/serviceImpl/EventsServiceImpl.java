package com.vehicle.events.serviceImpl;

import com.vehicle.events.domains.Events;
import com.vehicle.events.repo.EventsRepo;
import com.vehicle.events.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service(value = "eventsService")
public class EventsServiceImpl implements EventsService {

    @Autowired
    private EventsRepo eventsRepo;

    @Override
    public Mono<Events> findEventById(String eventId) {
        return eventsRepo.findById(eventId);
    }

    @Override
    public Flux<Events> findAll() {
        return eventsRepo.findAll().doOnComplete(this::toString);
    }

    @Override
    public Mono<Void> delete(String id) {
        return eventsRepo.deleteById(id);
    }

    @Override
    public Disposable createEvents(Events events) {
        System.out.println("---- in service:" + events);
        return eventsRepo.save(events).subscribe();
    }
}
