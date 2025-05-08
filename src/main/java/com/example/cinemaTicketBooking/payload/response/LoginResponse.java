package com.example.cinemaTicketBooking.payload.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private int userId;
}
