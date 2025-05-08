package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.payload.response.LoginResponse;
import com.example.cinemaTicketBooking.service.AuthenticationService;
import com.example.cinemaTicketBooking.service.imp.AuthenticationServiceImp;
import com.example.cinemaTicketBooking.utils.JwtHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestParam String email,@RequestParam String password) {
        String token=authenticationService.authenticate(email, password);
        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUserId(jwtHelper.getIdUserFromToken(token));
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("Success");
        baseResponse.setData(loginResponse);

        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(0) ;
        }


        return ResponseEntity.ok(principal.getName());
    }


}
