package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.payload.response.BaseResponse;
import com.example.cinemaTicketBooking.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("getAllProducts");
        baseResponse.setData(productService.getAllProducts());
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/by-type")
    public ResponseEntity<?> getProductsByType(@RequestParam String type) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("getAllProducts");
        baseResponse.setData(productService.findByType(type));
        baseResponse.setCode(200);
        return ResponseEntity.ok(baseResponse);
    }


}
