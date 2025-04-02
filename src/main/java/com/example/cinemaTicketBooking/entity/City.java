package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "City")
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "city")
    private List<Location> locations;


}
