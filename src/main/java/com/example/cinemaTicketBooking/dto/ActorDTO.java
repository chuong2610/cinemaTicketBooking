package com.example.cinemaTicketBooking.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActorDTO {
    private int id;
    private String name;
    private String description;
    private String img;
    private LocalDate birthday;
}
