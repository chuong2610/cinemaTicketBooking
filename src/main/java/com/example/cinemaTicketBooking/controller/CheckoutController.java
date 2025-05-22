package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.dto.CheckoutDTO;
import com.example.cinemaTicketBooking.dto.SeatDTO;
import com.example.cinemaTicketBooking.payload.request.CheckoutRequest;
import com.example.cinemaTicketBooking.payload.request.SeatReleaseRequest;
import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.CheckoutService;
import com.example.cinemaTicketBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;
    @PostMapping("/product")
    public ResponseEntity<?> checkoutProduct(@RequestBody CheckoutRequest request) {

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(checkoutService.checkoutInformation(request));
        baseResponse.setMessage("checout product");
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }



}
