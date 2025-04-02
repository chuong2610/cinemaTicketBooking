package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user-detail")
    public ResponseEntity<?> findById(@RequestParam int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userService.findById(id));
        baseResponse.setMessage("user_detail");
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }
}
