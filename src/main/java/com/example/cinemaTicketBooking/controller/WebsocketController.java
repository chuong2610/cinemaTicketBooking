package com.example.cinemaTicketBooking.controller;

import com.example.cinemaTicketBooking.dto.SeatDTO;
import com.example.cinemaTicketBooking.service.imp.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @MessageMapping("/seat.select")
    public void seatSelect(@Payload String message) {
        kafkaProducer.sendSeatSelection(message);
    }
}
