package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.dto.SeatDTO;
import com.example.cinemaTicketBooking.payload.request.SeatReleaseRequest;
import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

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

    @PostMapping("/release-selecting")
    public ResponseEntity<?> releaseTempSeats(@RequestBody SeatReleaseRequest request) {
        Set<String> keys = seatService.getSeatsByUserIdAndMovieSchedule(
                request.getUserId(),
                request.getMovieId(),
                request.getSchedule()
        );

        seatService.releaseSeats(seatService.getSeatDTOFromKey(keys));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-time")
    public ResponseEntity<?> resetTime(@RequestBody SeatReleaseRequest request) {
        Set<String> keys = seatService.getSeatsByUserIdAndMovieSchedule(
                request.getUserId(),
                request.getMovieId(),
                request.getSchedule()
        );

        seatService.resetTime(keys);

        return ResponseEntity.ok().build();
    }
}
