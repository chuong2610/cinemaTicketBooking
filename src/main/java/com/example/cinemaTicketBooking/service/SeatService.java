package com.example.cinemaTicketBooking.service;

import com.example.cinemaTicketBooking.dto.SeatDTO;
import com.example.cinemaTicketBooking.payload.response.BaseResponse;

import java.util.List;

public interface SeatService {
    BaseResponse processSeatSelection(String message);
    List<SeatDTO> getCurrentSeatStatus(SeatDTO message);

    List<SeatDTO> getSelectedSeats(SeatDTO seatDTO);
     double getSeatPrice(SeatDTO seatDTO);
}
