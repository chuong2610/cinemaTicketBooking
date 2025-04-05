package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.MovieDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovieService {
     List<MovieDTO> findByDateBefore(int page, int size);
     List<MovieDTO> findByDateAfter(int page, int size);
     List<MovieDTO> findTopMoviesByReviewCount(int page, int size);
     MovieDTO findById(int id);
     List<MovieDTO> findByLocationIdAndScheduleDate(int id, LocalDateTime startDate, LocalDateTime endDate, int page, int size);
     List<MovieDTO> findByLocationId(int id, int page, int size);

}
