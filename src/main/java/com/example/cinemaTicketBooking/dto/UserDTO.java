package com.example.cinemaTicketBooking.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private CardDTO cardDTO;
}
