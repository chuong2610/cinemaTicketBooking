package com.example.cinemaTicketBooking.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MovieDTO {
    private int id;

    private String title;

    private LocalDate date;

    private int duration;

    private String description;

    private float avrRating;

    private List<ImageDTO>imageDTOs;

    private List<ReviewDTO>reviewDTOs;

    private List<TicketOrderDTO>ticketOrderDTOs;

    private List<String> genres;



}
