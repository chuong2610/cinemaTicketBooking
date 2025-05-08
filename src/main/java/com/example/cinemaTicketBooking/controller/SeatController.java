package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.dto.SeatDTO;
import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;
    @GetMapping("/selecting")
    public ResponseEntity<?> getSeatSelecting(@RequestParam int movieId, @RequestParam LocalDateTime schedule) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setMovieId(movieId);
        seatDTO.setSchedule(schedule);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(seatService.getCurrentSeatStatus(seatDTO));
        baseResponse.setMessage("user_detail");
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/booked")
    public ResponseEntity<?> getSeatBooked(@RequestParam int movieId, @RequestParam LocalDateTime schedule) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setMovieId(movieId);
        seatDTO.setSchedule(schedule);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(seatService.getSelectedSeats(seatDTO));
        baseResponse.setMessage("user_detail");
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }
}
