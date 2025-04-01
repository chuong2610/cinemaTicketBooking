package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "TicketPrice")
public class TicketPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;

    private String seatType;



    @OneToOne(mappedBy = "ticketPrice")
    private TicketOrder ticketOrder;

}
