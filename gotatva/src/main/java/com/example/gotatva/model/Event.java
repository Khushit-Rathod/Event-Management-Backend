package com.example.gotatva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event name is required")
    private String name;

    @Future(message = "Event date must be in the future")
    private LocalDateTime date;

    @Positive(message = "Capacity must be positive")
    private int capacity;

    private int bookedSeats = 0;

    public Event(String name, LocalDateTime date, int capacity) {
        this.name = name;
        this.date = date;
        this.capacity = capacity;
    }

    public boolean isFull() {
        return bookedSeats >= capacity;
    }
}
