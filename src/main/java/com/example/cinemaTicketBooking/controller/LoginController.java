package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.AuthenticationService;
import com.example.cinemaTicketBooking.service.imp.AuthenticationServiceImp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;
    @GetMapping("/sign-in")
    public ResponseEntity<?> login(@RequestParam String email,@RequestParam String password) {
        String token=authenticationService.authenticate(email, password);
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("Success");
        baseResponse.setData(token);

        return ResponseEntity.ok(baseResponse);
    }
}
