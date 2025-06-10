package com.example.gotatva.controller;

import com.example.gotatva.model.Booking;
import com.example.gotatva.model.BookingRequest;
import com.example.gotatva.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/{eventId}/book")
    public ResponseEntity<?> bookSeat(
            @PathVariable Long eventId,
            @RequestBody BookingRequest request) {
        try {
            Booking booking = bookingService.bookSeat(
                    eventId,
                    request.getUsername(),
                    request.getEmail(),
                    request.getSeatNumber()
            );
            return ResponseEntity.ok(booking);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred");
        }
    }

    @GetMapping("/{eventId}/bookings")
    public ResponseEntity<List<Booking>> getEventBookings(@PathVariable Long eventId) {
        try {
            return ResponseEntity.ok(bookingService.getBookingsForEvent(eventId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}