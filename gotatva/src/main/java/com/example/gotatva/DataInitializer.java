package com.example.gotatva;

import com.example.gotatva.model.Event;
import com.example.gotatva.repo.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class DataInitializer implements CommandLineRunner {
    private final EventRepository eventRepository;

    public DataInitializer(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Add some test events
        Event event1 = new Event("Tech Conference",
                LocalDateTime.now().plus(10, ChronoUnit.DAYS), 100);
        Event event2 = new Event("Music Festival",
                LocalDateTime.now().plus(5, ChronoUnit.DAYS), 50);
        Event event3 = new Event("Art Exhibition",
                LocalDateTime.now().plus(3, ChronoUnit.DAYS), 30);

        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);
    }
}