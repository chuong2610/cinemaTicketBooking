package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Locations All");
        baseResponse.setCode(200);
        baseResponse.setData(locationService.findAll());
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/by-city")
    public ResponseEntity<?> findByCityId(@RequestParam int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Locations All");
        baseResponse.setCode(200);
        baseResponse.setData(locationService.findByCityId(id));
        return ResponseEntity.ok(baseResponse);
    }

}
