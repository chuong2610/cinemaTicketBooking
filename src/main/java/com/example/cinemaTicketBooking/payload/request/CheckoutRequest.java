package com.example.cinemaTicketBooking.payload.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckoutRequest {
    private int userId;
    private int movieId;
    private LocalDateTime schedule;
    private String location;
    private String address;
}
