package com.example.gotatva.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Positive(message = "Seat number must be positive")
    private int seatNumber;

    public Booking(Event event, String username, String email, int seatNumber) {
        this.event = event;
        this.username = username;
        this.email = email;
        this.seatNumber = seatNumber;
    }
}