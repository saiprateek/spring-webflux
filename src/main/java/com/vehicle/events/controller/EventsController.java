package com.vehicle.events.controller;

import com.vehicle.events.domains.Events;
import com.vehicle.events.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @RequestMapping(value = {"/create", "/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvents(@RequestBody Events events) {
        System.out.println("------- in controller" + events);
        eventsService.createEvents(events);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Mono<Events>> findById(@PathVariable("id") String id) {
        return new ResponseEntity<Mono<Events>>(eventsService.findEventById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Events>> findAll() {
        Flux<Events> events = eventsService.findAll();
        System.out.println("all data....." + events);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvents(@PathVariable("id") String id) {
        eventsService.delete(id).subscribe();
    }

    @RequestMapping(value = "/threads/webflux", method = RequestMethod.GET)
    public Flux<String> getThreadsWebflux() {
        return Flux.fromIterable(getThreads());
    }

    @GetMapping("/threads/mongodb")
    public Flux<String> getIndexMongo() {
        eventsService.findAll()
                .doOnNext(event -> System.out.print("Event: {}" + event))
                .subscribe();
        return Flux.fromIterable(getThreads());
    }


    private List<String> getThreads() {
        return Thread.getAllStackTraces()
                .keySet()
                .stream()
                .map(t -> String.format("%-20s \t %s \t %d \t %s\n", t.getName(), t.getState(), t.getPriority(), t.isDaemon() ? "Daemon" : "Normal"))
                .collect(Collectors.toList());
    }
}
