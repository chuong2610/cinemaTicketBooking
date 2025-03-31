package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Card")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int point;

    private String description;

    @OneToOne
    @JoinColumn(name = "customerId")
    private User customer;
}
