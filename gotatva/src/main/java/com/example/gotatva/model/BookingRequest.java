package com.example.gotatva.model;

import lombok.Data;

@Data
public class BookingRequest {
    private String username;
    private String email;
    private int seatNumber;
}