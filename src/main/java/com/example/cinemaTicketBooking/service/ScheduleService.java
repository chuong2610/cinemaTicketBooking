package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.ScheduleDTO;
import com.example.cinemaTicketBooking.entity.Schedule;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface ScheduleService {
    List<ScheduleDTO> findByRoomLocationIdAndMovieIdAndDate(int locationId, int movieId, LocalDate date);
}
