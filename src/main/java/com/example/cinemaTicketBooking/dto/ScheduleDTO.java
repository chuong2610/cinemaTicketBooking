package com.example.cinemaTicketBooking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDTO {
    private int id;
    private LocalDateTime date;
}
