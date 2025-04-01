package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Movie")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private LocalDate date;

    private int duration;

    private String description;

    @OneToMany(mappedBy = "movie")
    private List<MovieActor> movieActors;

    @OneToMany(mappedBy = "movie")
    private List<MovieGenre> movieGenres;

    @OneToMany(mappedBy = "movie")
    private List<Image> images;

    @OneToMany(mappedBy = "movie")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    @OneToMany(mappedBy = "movie")
    private List<TicketOrder> ticketOrders;



}
