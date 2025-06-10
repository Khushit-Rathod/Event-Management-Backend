package com.example.gotatva.controller;

import com.example.gotatva.model.Event;
import com.example.gotatva.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        return ResponseEntity.ok(eventService.getUpcomingEvents());
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        if (eventService.existsByNameAndDate(event.getName(), event.getDate())) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(eventService.saveEvent(event));
    }
}