package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;


@Data
@Entity(name="Actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate birthday;
    private String description;

    @OneToMany(mappedBy = "actor")
    private List<MovieActor> movieActors;
}
