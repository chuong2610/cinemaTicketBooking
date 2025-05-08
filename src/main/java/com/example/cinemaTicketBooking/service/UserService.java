package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.UserDTO;

import java.time.LocalDateTime;

public interface UserService {
    UserDTO findById(int id);
    void syncTemporaryUserToRealUser(int realUserId, int tempUserId, int movieId, LocalDateTime schedule);
}
