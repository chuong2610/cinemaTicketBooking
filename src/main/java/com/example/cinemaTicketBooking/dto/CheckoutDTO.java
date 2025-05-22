package com.example.cinemaTicketBooking.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class CheckoutDTO {
    private int userId;
    private int movieId;
    private String location;
    private String address;
    private LocalDateTime schedule;
    private List<String> seats;
    private Map<String,Integer> products;
    private double totalPrice;
}
