package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    private String type;

    @OneToOne(mappedBy = "seat")
    private TicketOrder ticketOrder;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;


}
