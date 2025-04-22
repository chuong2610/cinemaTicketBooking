package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.ScheduleDTO;
import com.example.cinemaTicketBooking.entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ScheduleService {
    List<ScheduleDTO> findByRoomLocationIdAndMovieId(int locationId, Integer movieId);
}
