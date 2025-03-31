package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private User customer;
}
