package com.example.cinemaTicketBooking.dto;

import com.example.cinemaTicketBooking.entity.Movie;
import lombok.Data;

@Data
public class TicketOrderDTO {
    private int id;

    private MovieDTO movieDTO;
}
