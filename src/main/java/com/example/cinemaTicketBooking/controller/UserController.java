package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.SeatService;
import com.example.cinemaTicketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    @PostMapping("/user-change")
    public ResponseEntity<?> syncTemporaryUserToRealUser(@RequestParam int realUserId,@RequestParam int tempUserId,@RequestParam int movieId,@RequestParam  LocalDateTime schedule) {
        userService.syncTemporaryUserToRealUser(realUserId, tempUserId, movieId, schedule);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }
}
