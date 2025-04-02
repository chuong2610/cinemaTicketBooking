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
@RequestMapping("locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<?> findAll(@RequestParam int page, @RequestParam int size) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Locations All");
        baseResponse.setCode(200);
        baseResponse.setData(locationService.findAll(page, size));
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping(params = {"id" , "page", "size"})
    public ResponseEntity<?> findByCityId(@RequestParam int id,@RequestParam int page, @RequestParam int size) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Locations All");
        baseResponse.setCode(200);
        baseResponse.setData(locationService.findByCityId(id,page, size));
        return ResponseEntity.ok(baseResponse);
    }

}
