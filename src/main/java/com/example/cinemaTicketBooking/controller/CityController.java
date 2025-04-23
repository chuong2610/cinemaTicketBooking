package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<?> findCities() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("cities");
        baseResponse.setCode(200);
        baseResponse.setData(cityService.findCities());
        return ResponseEntity.ok(baseResponse);
    }
}
