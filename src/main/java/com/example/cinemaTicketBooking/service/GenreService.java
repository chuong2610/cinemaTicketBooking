package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    List<GenreDTO> findGenres(int page, int size);
}
