package com.example.cinemaTicketBooking.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private double price;
    private String img;
}
