package com.example.gotatva.service;

import com.example.gotatva.model.Booking;
import com.example.gotatva.model.Event;
import com.example.gotatva.repo.BookingRepository;
import com.example.gotatva.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    public Booking bookSeat(Long eventId, String username, String email, int seatNumber) {
        Event event = eventService.getEventById(eventId);

        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }

        if (event.isFull()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event is full");
        }

        if (bookingRepository.existsByEventAndEmail(event, email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have already booked a seat for this event");
        }

        if (bookingRepository.existsByEventAndSeatNumber(event, seatNumber)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat number " + seatNumber + " is already taken");
        }

        Booking booking = new Booking(event, username, email, seatNumber);
        event.setBookedSeats(event.getBookedSeats() + 1);
        eventRepository.save(event);

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsForEvent(Long eventId) {
        Event event = eventService.getEventById(eventId);

        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }

        return bookingRepository.findByEvent(event);
    }
}