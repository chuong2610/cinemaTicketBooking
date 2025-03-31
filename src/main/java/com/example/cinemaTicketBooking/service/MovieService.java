package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.MovieDTO;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    public List<MovieDTO> findByDateBefore();
    public List<MovieDTO> findByDateAfter();
    public List<MovieDTO> findTopMoviesByReviewCount(int page, int size);
}
