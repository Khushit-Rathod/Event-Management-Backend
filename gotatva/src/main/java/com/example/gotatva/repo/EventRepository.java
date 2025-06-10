package com.example.gotatva.repo;

import com.example.gotatva.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.date > :currentDate ORDER BY e.date ASC")
    List<Event> findUpcomingEvents(LocalDateTime currentDate);

    boolean existsByNameAndDate(String name, LocalDateTime date);
}