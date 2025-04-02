package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Card")
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int point;

    private String name;

    @OneToOne
    @JoinColumn(name = "customerId")
    private User customer;
}
