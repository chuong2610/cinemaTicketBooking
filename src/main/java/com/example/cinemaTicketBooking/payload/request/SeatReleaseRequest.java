package com.example.cinemaTicketBooking.payload.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeatReleaseRequest {
    private int userId;
    private int movieId;
    private LocalDateTime schedule;
}
