package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping
@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @GetMapping("/schedule")
    public ResponseEntity<?>findByRoomLocationIdAndMovieId( @RequestParam int locationId,@RequestParam int movieId){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(scheduleService.findByRoomLocationIdAndMovieId(locationId, movieId));
        baseResponse.setMessage("Now showing");
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }
}
