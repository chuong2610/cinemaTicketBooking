package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity(name = "TicketOrder")
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "ticketPriceId")
    private TicketPrice ticketPrice;

    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill;

    @OneToOne
    @JoinColumn(name = "seatId")
    private Seat seat;
}
