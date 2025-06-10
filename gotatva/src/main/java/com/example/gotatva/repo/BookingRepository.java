package com.example.gotatva.repo;

import com.example.gotatva.model.Booking;
import com.example.gotatva.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByEvent(Event event);

    boolean existsByEventAndSeatNumber(Event event, int seatNumber);

    boolean existsByEventAndEmail(Event event, String email);
}