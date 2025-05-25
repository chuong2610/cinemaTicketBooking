package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.dto.CheckoutDTO;
import com.example.cinemaTicketBooking.payload.request.MomoRequest;
import com.example.cinemaTicketBooking.payload.response.MomoResponse;
import com.example.cinemaTicketBooking.service.BillService;
import com.example.cinemaTicketBooking.service.MomoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/momo")
public class MomoController {
    @Value("${momo.endpoint}")
    private String endpoint;
    @Autowired
    private MomoService momoService;
    @Autowired
    private BillService billService;
    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(@RequestBody CheckoutDTO checkoutDTO) throws Exception {
        int billId=billService.createBill(checkoutDTO);
        MomoRequest request = momoService.getMomoRequest(checkoutDTO,billId);
        System.out.println(request);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MomoRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> momoResponse = restTemplate.postForEntity(endpoint, entity, String.class);
        return ResponseEntity.ok(momoResponse.getBody());
    }
    @PostMapping("/notify")
    public ResponseEntity<?> momoNotify(@RequestBody MomoResponse momoResponse) throws Exception {
        System.out.println("Notify từ MoMo: " + momoResponse);
        momoService.processMomoIpn(momoResponse);
        return ResponseEntity.ok("OK");
    }
}
