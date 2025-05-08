package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.SeatDTO;
import com.example.cinemaTicketBooking.payload.response.BaseResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface SeatService {
    BaseResponse processSeatSelection(String message);
    List<SeatDTO> getCurrentSeatStatus(SeatDTO message);

    List<SeatDTO> getSelectedSeats(SeatDTO seatDTO);
     double getSeatPrice(SeatDTO seatDTO);
    Set<String> getSeatsByUserIdAndMovieSchedule(int userId, int movieId, LocalDateTime schedule);
    void releaseSeats( List<SeatDTO> seats);
    List<SeatDTO> getSeatDTOFromKey(Set<String> keys);
    void resetTime(Set<String> keys);
}
