package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Autowired
    private ActorService actorService;
    @GetMapping("/actor-detail")
    public ResponseEntity<?> findById(@RequestParam int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(actorService.findById(id));
        baseResponse.setMessage("actor_detail");
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }
}
