package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.MovieDTO;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    public List<MovieDTO> findByDateBefore(int page, int size);
    public List<MovieDTO> findByDateAfter(int page, int size);
    public List<MovieDTO> findTopMoviesByReviewCount(int page, int size);
}
