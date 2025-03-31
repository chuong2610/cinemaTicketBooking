package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity(name = "Image")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String img;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;
}
