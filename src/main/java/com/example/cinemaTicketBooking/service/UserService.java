package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.UserDTO;

public interface UserService {
    UserDTO findById(int id);
}
