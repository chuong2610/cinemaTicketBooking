package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.dto.CheckoutDTO;
import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.repository.BillRepository;
import com.example.cinemaTicketBooking.service.imp.BillServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillServiceImp billService;

    @PostMapping("/create")
    public ResponseEntity<?> createBill(@RequestBody CheckoutDTO checkoutDTO){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("Success");
        baseResponse.setData(billService.createBill(checkoutDTO));
        return ResponseEntity.ok(baseResponse);
    }
}
