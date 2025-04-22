package com.example.cinemaTicketBooking.dto;

import com.example.cinemaTicketBooking.entity.Movie;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ReviewDTO {
    private int id;

    private String description;

    private int rating;

    private UserDTO userDTO;

    private MovieDTO movieDTO;
}
