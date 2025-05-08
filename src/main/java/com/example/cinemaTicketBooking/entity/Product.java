package com.example.cinemaTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private double price;
    private String img;

    @OneToMany(mappedBy = "product")
    private List<ProductOrder> productOrders;
}
