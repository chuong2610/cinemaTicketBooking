package com.example.cinemaTicketBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class SeatDTO {
    private int movieId;

    private LocalDateTime schedule;

    private String row;

    private int number;

    private int userId;

    private String action;

    private long price;




}
