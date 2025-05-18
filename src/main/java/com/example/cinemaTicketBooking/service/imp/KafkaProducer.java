package com.example.cinemaTicketBooking.service.imp;

import com.example.cinemaTicketBooking.dto.SeatDTO;
import com.example.cinemaTicketBooking.repository.SeatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KafkaProducer {
    @Autowired


    private final KafkaTemplate<String, String> kafkaTemplate;



    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Gửi ghế đã chọn lên Kafka
    public void sendSeatSelection(String message) {
        kafkaTemplate.send("seat-selection-topic", message);
    }


}