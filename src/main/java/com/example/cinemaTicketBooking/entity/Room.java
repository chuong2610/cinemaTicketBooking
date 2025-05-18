package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;

    @OneToMany(mappedBy = "room")
    private List<Seat> seats;
    @OneToMany(mappedBy = "room")
    private List<Schedule> schedules;
}
